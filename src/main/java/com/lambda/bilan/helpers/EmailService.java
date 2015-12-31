package com.lambda.bilan.helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lambda.bilan.dao.CollaborateurDAO;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Utilisateur;

@Component
@Scope("prototype")
public class EmailService {
	private final static String TEMPLATE_NEW_USER="template/mail-new-created-template.vm";
	private final static String TEMPLATE_PASSWORD_FORGET="template/mail-forget-password-template.vm";
	private final static String TEMPLATE_FEEDBACK_VALIDE="template/mail-feedback-valide-template.vm";
	private final static String TEMPLATE_ALERT_MANAGERRH="template/mail-managerRH-alert-template.vm";

	@Autowired
	EmailThread emailThread;
	@Autowired
	CollaborateurDAO collaborateurDAO;

	public void sendMailNewUtilisateur(Utilisateur utilisateur) {		
		EmailModel model = new EmailModel();
		model.addModel(utilisateur.getNomUtilisateur());
		model.addModel(utilisateur.getPasswordUtilisateur());
		emailThread.setModel(model);
		emailThread.setEmail(utilisateur.getEmailUtilisateur());
		emailThread.setTemplate(TEMPLATE_NEW_USER);
		emailThread.run();
	}

	
	public void sendMailForgetPassword(Utilisateur utilisateur) {		
		EmailModel model = new EmailModel();
		model.addModel(utilisateur.getNomUtilisateur());
		model.addModel(utilisateur.getPasswordUtilisateur());
		emailThread.setModel(model);
		emailThread.setEmail(utilisateur.getEmailUtilisateur());
		emailThread.setTemplate(TEMPLATE_PASSWORD_FORGET);
		emailThread.run();
	}
	
	public void sendMailFeedbackValide(List<Utilisateur> utilisateurs , String nomCollaborateur,String nomProjet) {		
		List<EmailModel> models = new ArrayList<EmailModel>();
		List<String> mails =new ArrayList<String>();
		EmailModel mailModel;
		for (Utilisateur utilisateur : utilisateurs) {
			mailModel = new EmailModel();
			mailModel.addModel(utilisateur.getNomUtilisateur());
			mailModel.addModel(nomCollaborateur);
			mailModel.addModel(nomProjet);
			models.add(mailModel);
			mails.add(utilisateur.getEmailUtilisateur());
		}
		emailThread.setModel(models);
		emailThread.setEmail(mails);
		emailThread.setTemplate(TEMPLATE_FEEDBACK_VALIDE);
		emailThread.run();
	}

	public void sendMailsManagerRH() {

		List<EmailModel> models = new ArrayList<EmailModel>();
		List<String> mails =new ArrayList<String>();

		boolean etat=false;
		EmailModel mailModel;
		int i=0,j=0;
		for(Collaborateur collaborateur : collaborateurDAO.getAllCollaborateurBAP()){
			if(j==0){
				mailModel = new EmailModel();
				mailModel.addModel(collaborateur.getManagerRH().getIdUtilisateur().toString());
				mailModel.addModel(collaborateur.getManagerRH().getNomUtilisateur());
				mailModel.addModel(Calendar.getInstance().getTime().toString());
				mailModel.addModel(collaborateur.getNomUtilisateur()+" "+collaborateur.getPrenomUtilisateur()+"  ID: #"+collaborateur.getIdUtilisateur());
				models.add(mailModel);
				mails.add(collaborateur.getManagerRH().getEmailUtilisateur());
			}
			do {
				if(models.get(i).getModels().get(0).equals(collaborateur.getManagerRH().getIdUtilisateur().toString())){
					models.get(i).getModels().set(3, models.get(i).getModels().get(3)+"<br/>"+collaborateur.getNomUtilisateur()+" "+collaborateur.getPrenomUtilisateur()+"  ID: #"+collaborateur.getIdUtilisateur());
					etat=true;
				}else{
					i++;
					mailModel = new EmailModel();
					mailModel.addModel(collaborateur.getManagerRH().getIdUtilisateur().toString());
					mailModel.addModel(collaborateur.getManagerRH().getNomUtilisateur());
					mailModel.addModel(Calendar.getInstance().getTime().toString());
					mailModel.addModel(collaborateur.getNomUtilisateur()+" "+collaborateur.getPrenomUtilisateur()+"  ID: #"+collaborateur.getIdUtilisateur());
					models.add(mailModel);
					mails.add(collaborateur.getManagerRH().getEmailUtilisateur());
				}
			} while (etat==false);
			j=1;
		}
		
		emailThread.setModel(models);
		emailThread.setEmail(mails);
		emailThread.setTemplate(TEMPLATE_ALERT_MANAGERRH);
		emailThread.run();

	}

}
