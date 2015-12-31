package com.lambda.bilan.metier;

import java.util.Date;

import com.lambda.bilan.domain.PlanAmelioration;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.helpers.LambdaException;

public interface IPlanAmeliorationMetier {
	
	public PlanAmelioration getPlanAmeliorationOfCollaborateurByYear(Collaborateur collaborateur, Date dateBAP) throws LambdaException; // *
	public void addPlanAmelioration(PlanAmelioration planAmelioration) throws LambdaException;

}
