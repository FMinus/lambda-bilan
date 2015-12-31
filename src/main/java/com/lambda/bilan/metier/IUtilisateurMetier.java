package com.lambda.bilan.metier;

import java.util.List;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.ManagerRH;
import com.lambda.bilan.entities.Utilisateur;
import com.lambda.bilan.helpers.LambdaException;


public interface IUtilisateurMetier {
	
		
		public List<Collaborateur> getAllCollaborateurOfManagerRH(Long idManagerRH) throws LambdaException;
		public List<Collaborateur> getAllCollaborateurOfProjet(Long idProjet) throws LambdaException;
		public List<Collaborateur> getAllCollaborateurWithoutProject() throws LambdaException;
		public List<Collaborateur> getAllCollaborateurWithoutObjectif() throws LambdaException;
		public List<Collaborateur> getAllOldCollaborateur() throws LambdaException; // *
				
		public void addUtilisateur(Utilisateur utilisateur) throws LambdaException; // *
		public void updateUtilisateur(Utilisateur utilisateur) throws LambdaException; // *
		public void updatePassword(String password , Long id) throws LambdaException;
		public void updateIdCalendrier(Long idUtilisateur, String idCalendrierUtilisateur) throws LambdaException;
		public void updateUrlPhotoUtilisateur(Long idUtilisateur, String urlPhotoUtilisateur)throws LambdaException;
		public void forgetPassword(Utilisateur utilisateur) throws LambdaException;
		public void deleteUtilisateur(Long id) throws LambdaException; // *
		public Utilisateur getUtilisateur(Long id) throws LambdaException;
		public List<Utilisateur> getAllUtilisateur() throws LambdaException; // *
		public void departCollaborateur(Long id) throws LambdaException; // *
		public List<ManagerRH> getAllManagerRH() throws LambdaException; // *
		public List<Evaluateur> getAllEvaluateur() throws LambdaException; // *
		public Utilisateur login(String emailUtilisateur,String passwordUtilisateur) throws LambdaException;

}
