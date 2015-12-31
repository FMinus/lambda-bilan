package com.lambda.bilan.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.lambda.bilan.dao.AdministrateurDAO;
import com.lambda.bilan.dao.InterventionDAO;
import com.lambda.bilan.dao.NoteDAO;
import com.lambda.bilan.domain.FeedBack;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Intervention;
import com.lambda.bilan.entities.Note;
import com.lambda.bilan.entities.Utilisateur;
import com.lambda.bilan.helpers.DateHelper;
import com.lambda.bilan.helpers.EmailService;
import com.lambda.bilan.helpers.GoogleAgendaThread;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;

@Service("FeedBack")
@Transactional(rollbackFor= LambdaException.class)
public class FeedBackMetier implements IFeedBackMetier {

	@Autowired
	private InterventionDAO interventionDAO;
	@Autowired
	private NoteDAO noteDAO;
	@Autowired
	private AdministrateurDAO administrateurDAO;
	@Autowired
	private EmailService mailService;
	@Autowired
	GoogleAgendaThread googleAgendaThread;

	@Override
	public List<FeedBack> getAllfeedBackOfCollaborateurByYear(Collaborateur collaborateur, Date dateBAP)  throws LambdaException {
		try {
			FeedBack feedBack;
			Date dateDebut = DateHelper.dateSubtractYear(dateBAP);
			Date dateFin = dateBAP;
			List<Note> notes;
			List<FeedBack> feedBacks = new ArrayList<FeedBack>();
			List<Intervention> interventions=interventionDAO.findByDateFinInterventionBetweenAndCollaborateur(dateDebut, dateFin, collaborateur);
			for(Intervention intervention : interventions){
				feedBack=new FeedBack();
				notes =new ArrayList<Note>();
				feedBack.setIntervention(intervention);
				notes = intervention.getNotes();
				int nbrThemeQualifies=0;
				int totalePoids=0;
				for (Note note : notes) {
					if(note.getQualification()!=null){
						nbrThemeQualifies++;
						totalePoids+=note.getQualification().getPoidsQualification();
					}						
				}
				feedBack.setNbrThemeQualifies(nbrThemeQualifies);
				feedBack.setTotalePoids(totalePoids);
				feedBacks.add(feedBack);
				if(nbrThemeQualifies==0)
					feedBack.setNoteGlobale(0);
				else
				feedBack.setNoteGlobale(totalePoids/nbrThemeQualifies);
				nbrThemeQualifies=0;
				totalePoids=0;
			}
			return feedBacks;

		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("feedback.list.empty"));
		}
	}


	@Override
	public void addFeedBack(List<Note> notes)throws LambdaException {
		try {
			noteDAO.save(notes);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LambdaException(PropretiesHelper.getText("feedback.add.fail"));
		}
	}


	@Override
	public void validerFeedBack(Long idIntervention) throws LambdaException {
		try {
			//validation
			interventionDAO.validerFeedback(idIntervention);
			//send mails
			Intervention intervention =interventionDAO.findOne(idIntervention);
			String nomProjet = intervention.getProjet().getNomProjet();
			String nomCollaborateur =intervention.getCollaborateur().getNomUtilisateur();
			List<Utilisateur> utilisateurs = Lists.newArrayList(administrateurDAO.findAll());
			utilisateurs.add(intervention.getCollaborateur().getManagerRH());
			utilisateurs.add(intervention.getCollaborateur());
			mailService.sendMailFeedbackValide(utilisateurs, nomCollaborateur, nomProjet);
			//Création d’entré dans Google Agenda
			List<String> list = new ArrayList<String>();
			list.add("lambda.bilan@gmail.com");
			list.add(intervention.getCollaborateur().getIdCalendrierUtilisateur());
			list.add(intervention.getProjet().getEvaluateur().getIdCalendrierUtilisateur());
	    	googleAgendaThread.setCalendarIDs(list);
	    	googleAgendaThread.setDate(DateHelper.getCurrentDate());
	    	googleAgendaThread.setTitre("Validation du Feedback");
	    	googleAgendaThread.setDescription("le feedback de "+nomCollaborateur+" sur le projet "+nomProjet+" est validé par "+intervention.getProjet().getEvaluateur().getNomUtilisateur());
	    	googleAgendaThread.run();
			
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("feedback.valide.fail"));
		}
	}

}
