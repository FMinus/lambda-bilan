package com.lambda.bilan.metier;

import java.util.List;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.helpers.LambdaException;

public interface IProjetMetier {
	
	public void addProjet(Projet projet)  throws LambdaException;
	public void updateProjet(Projet projet )  throws LambdaException;
	public void deleteProjet(Long id)  throws LambdaException;
	public Projet getProjet(Long id) throws LambdaException;
	
	public List<Projet> getAllProjet() throws LambdaException;
	public List<Projet> getAllProjetOfEvaluateur(Evaluateur evaluateur) throws LambdaException;
	public List<Projet> getAllProjetOfCollaborateur(Collaborateur collaborateur) throws LambdaException;
}
