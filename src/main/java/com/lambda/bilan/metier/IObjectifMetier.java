package com.lambda.bilan.metier;

import java.util.Date;
import java.util.List;

import com.lambda.bilan.domain.FicheObjectifs;
import com.lambda.bilan.entities.Categorie;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Objectif;
import com.lambda.bilan.helpers.LambdaException;

public interface IObjectifMetier {

	public void addObjectif(List<Objectif> objectifs) throws LambdaException;
	public void updateObjectif(Objectif objectif) throws LambdaException;
	public void deleteObjectif(Long id) throws LambdaException;
	public Objectif getObjectif(Long id) throws LambdaException;
	public void updateObjectifs(List<Objectif> objectifs) throws LambdaException;
	public void validerObjectif(Long id) throws LambdaException;
	public void refuserObjectif(Long id) throws LambdaException;

	public List<Objectif> getAllObjectifsOfCollaborateurByYear(Collaborateur collaborateur,Date dateBAP) throws LambdaException;
	public List<Objectif> getAllObjectifsOfCollaborateurThisYear(Collaborateur collaborateur) throws LambdaException;
	public List<Objectif> getAllObjectifsRefusFromCollaborateurOfManagerRH(Long id) throws LambdaException;
	public FicheObjectifs getFicheObjectifsOfCollaborateurByYear(Collaborateur collaborateur,Date dateBAP) throws LambdaException;
	public FicheObjectifs getFicheObjectifsOfCollaborateur(Collaborateur collaborateur,Date dateBAP) throws LambdaException;
	public List<Categorie> getAllCategorie() throws LambdaException;
}
