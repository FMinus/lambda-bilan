package com.lambda.bilan.metier;

import java.util.List;

import com.lambda.bilan.entities.Intervention;
import com.lambda.bilan.entities.Qualification;
import com.lambda.bilan.entities.Theme;
import com.lambda.bilan.helpers.LambdaException;

public interface IInterventionMetier {
	
	public void assignProjet(List<Intervention> interventions) throws LambdaException;
	public void defineIntervention(Intervention intervention) throws LambdaException;
	public List<Intervention> getTop6FinishedInterventionOfEvaluateur(Long id) throws LambdaException;
	public Intervention getIntervention(Long idProjet, Long idCollaborateur ) throws LambdaException;
	public List<Theme> getAllTheme()throws LambdaException;
	public List<Qualification> getAllQualification()throws LambdaException;
}
