package com.lambda.bilan.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.bilan.dao.ActionDAO;
import com.lambda.bilan.dao.PlanFormationDAO;
import com.lambda.bilan.domain.PlanAmelioration;
import com.lambda.bilan.entities.Action;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.PlanFormation;
import com.lambda.bilan.helpers.DateHelper;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;

@Service
@Transactional(rollbackFor= LambdaException.class)
public class PlanAmeliorationMetier implements IPlanAmeliorationMetier {

	@Autowired
	private PlanFormationDAO planFormationDAO;
	@Autowired
	private ActionDAO actionDAO ;


	@Override
	public PlanAmelioration getPlanAmeliorationOfCollaborateurByYear(Collaborateur collaborateur, Date dateBAP)throws LambdaException {
		try {
			List<PlanFormation> planFormations; 
			List<Action> actions;
			planFormations= planFormationDAO.findByObjectifs_dateCreationObjectifBetweenAndObjectifs_Collaborateur(DateHelper.dateSubtractYear(dateBAP), dateBAP, collaborateur);
			actions=actionDAO.findByDateActionBetweenAndCollaborateur(DateHelper.dateSubtractYear(dateBAP),dateBAP, collaborateur);	
			return new PlanAmelioration(planFormations, actions);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("planAmelioration.load.fail"));
		}
	}

	@Override
	public void addPlanAmelioration(PlanAmelioration planAmelioration) throws LambdaException {
		try {
			planFormationDAO.save(planAmelioration.getPlanFormations());
			actionDAO.save(planAmelioration.getActions());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("planAmelioration.add.fail"));
		}

	}

}
