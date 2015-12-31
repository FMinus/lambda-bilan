/**
 * 
 */
package com.lambda.bilan.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class EmailThread extends Thread {
	
	@Autowired
	private EmailSender emailSender;
	private List<String> email;
	private List<EmailModel> model;
	private String template;
	
	@Override
	public void run() {
		try {
			
			System.out.println(">>SENDING START");//Debug
			
			//iterate over emails
			for(int i=0; i<email.size(); i++){
				//get data
				String email = this.email.get(i);
				EmailModel model = this.model.get(i);
				
				//send emails
				System.out.println(">>SENDING TO:" + email);
				emailSender.sendMail(email, template, model);
				System.out.println(">>SENT TO:" + email);
			}
			
			System.out.println(">>SENDING END");//Debug	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(">>Error !");
		}finally{
			//this.interrupt();
		}
	}

	//signal email
	public void setModel(EmailModel model) {
		this.model = new ArrayList<EmailModel>();
		this.model.add(model);
	}

	public void setEmail(String email) {
		this.email = new ArrayList<String>();
		this.email.add(email);
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getTemplate() {
		return template;
	}

	//several emails
	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public List<EmailModel> getModel() {
		return model;
	}

	public void setModel(List<EmailModel> model) {
		this.model = model;
	}
	
}
