package com.lambda.bilan.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.api.client.util.Lists;
import com.lambda.bilan.dao.InterventionDAO;
import com.lambda.bilan.dao.QualificationDAO;
import com.lambda.bilan.dao.ThemeDAO;
import com.lambda.bilan.entities.Intervention;
import com.lambda.bilan.entities.Qualification;
import com.lambda.bilan.entities.Theme;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;

@Service("Intervention")
@Transactional
public class InterventionMetier implements IInterventionMetier {

	@Autowired
	private InterventionDAO interventionDAO;
	@Autowired
	private ThemeDAO themeDAO;
	@Autowired
	private QualificationDAO qualificationDAO;

	@Override
	public void assignProjet(List<Intervention> interventions) throws LambdaException {
		try {
			interventionDAO.save(interventions);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("intervention.add.fail"));
		}
	}

	@Override
	public void defineIntervention(Intervention intervention) throws LambdaException {
		
		try{
			Intervention intervention2 =interventionDAO.findOne(intervention.getIdIntervention());
			if(intervention2==null ){
				throw new LambdaException(PropretiesHelper.getText("intervention.update.fail"));
			} else{
				intervention.setProjet(intervention2.getProjet());
				intervention.setCollaborateur(intervention2.getCollaborateur());
				intervention.setEtatIntervention(false);
				interventionDAO.save(intervention);
			}
		} catch (IllegalArgumentException e) {
			throw new LambdaException(PropretiesHelper.getText("intervention.id.notfound"));
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("intervention.update.fail"));
		}
		
	}

	@Override
	public List<Intervention> getTop6FinishedInterventionOfEvaluateur(Long id) throws LambdaException {
		try {
			return interventionDAO.findTop6ByProjet_IdEvaluateurAndDateFinInterventionLessThanOrderByDateFinInterventionDesc(id,new Date());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("feedback.add.fail"));
		}
	}

	@Override
	public Intervention getIntervention(Long idProjet, Long idCollaborateur) throws LambdaException {
		try {
			return interventionDAO.findOneByIdProjetAndIdCollaborateur(idProjet, idCollaborateur);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("intervention.id.notfound"));
		}
	}

	@Override
	public List<Theme> getAllTheme() throws LambdaException {
		try {
			return Lists.newArrayList(themeDAO.findAll());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("general.list.load.fail"));
		}
	}

	@Override
	public List<Qualification> getAllQualification() throws LambdaException {
		try {
			return Lists.newArrayList(qualificationDAO.findAll());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("general.list.load.fail"));
		}
	}

}
