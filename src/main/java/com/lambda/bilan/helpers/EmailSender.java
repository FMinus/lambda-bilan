package com.lambda.bilan.helpers;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;


@Component
@Scope("prototype")
public class EmailSender {

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private VelocityEngine velocityEngine;
	
	public void sendMail(final String to, final String template,final EmailModel model ) throws Exception {
		
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
        	
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setSubject("NoReply");
                message.setTo(to);
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("model", model);
                @SuppressWarnings("deprecation")
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, map);
                message.setText(text, true);
            }
        };
        
        this.sender.send(preparator);
}
	
	public JavaMailSender getMailSender() {
		return sender;
	}

	public void setMailSender(JavaMailSender sender) {
		this.sender = sender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}
