package com.lambda.bilan.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lambda.bilan.dao.CollaborateurDAO;
import com.lambda.bilan.dao.EvaluateurDAO;
import com.lambda.bilan.dao.ManagerRHDAO;
import com.lambda.bilan.dao.UtilisateurDAO;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.ManagerRH;
import com.lambda.bilan.entities.Utilisateur;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.EmailService;
import com.lambda.bilan.helpers.PropretiesHelper;
import com.lambda.bilan.helpers.RandomGenerator;

@Service
@Transactional (rollbackFor= LambdaException.class)
public class UtilisateurMetier implements IUtilisateurMetier {

	@Autowired
	private UtilisateurDAO utilisateurDao;	
	@Autowired
	private CollaborateurDAO collaborateurDAO;
	@Autowired
	private ManagerRHDAO managerRHDAO;
	@Autowired
	private EvaluateurDAO evaluateurDAO;
	@Autowired
	private EmailService mailMetier;

	//Les collaborateurs d'un Manager RH
	@Override
	public List<Collaborateur> getAllCollaborateurOfManagerRH(Long idManagerRH)  throws LambdaException {
		try {
			return collaborateurDAO.getAllCollaborateurOfManagerRH( idManagerRH);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("collaborateur.list.load.fail"));
		}
	}

	//Les collaborateurs intervenants dans un Projet
	@Override
	public List<Collaborateur> getAllCollaborateurOfProjet(Long idProjet) throws LambdaException {
		try {
			return collaborateurDAO.getAllCollaborateurOfProjet(idProjet);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("collaborateur.list.load.fail"));
		}
	}

	//Les collaborateur qui en pas des projets
	@Override
	public List<Collaborateur> getAllCollaborateurWithoutProject() throws LambdaException {
		try {
			return collaborateurDAO.getAllCollaborateurWithoutProject();
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("collaborateur.list.load.fail"));
		}
	}

	//Les collaborateur qui en pas des objectifs
	@Override
	public List<Collaborateur> getAllCollaborateurWithoutObjectif() throws LambdaException {
		try {
			return  collaborateurDAO.getAllCollaborateurWithoutObjectif();
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("collaborateur.list.load.fail"));
		}
	}

	@Override
	public void addUtilisateur(Utilisateur utilisateur) throws LambdaException{
		try {
			//Generer un password
			String passwordUtilisateur = RandomGenerator.randomString();
			utilisateur.setPasswordUtilisateur(passwordUtilisateur);
			//Sauvgarder
			utilisateurDao.save(utilisateur);
			//Send mail password
			mailMetier.sendMailNewUtilisateur(utilisateur);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new LambdaException(PropretiesHelper.getText("utilisateur.add.fail"));
		}

	}


	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws LambdaException {
		try {
			if(utilisateur.getIdUtilisateur()==null)
				throw new LambdaException(PropretiesHelper.getText("utilisateur.update.fail"));
			else{
				utilisateur.setPasswordUtilisateur(utilisateurDao.findOne(utilisateur.getIdUtilisateur()).getPasswordUtilisateur());
				utilisateurDao.save(utilisateur);
			}

		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.update.fail"));
		}
	}

	@Override
	public void updatePassword(String password, Long id) throws LambdaException {
		try {
			utilisateurDao.updatePassword(password, id);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.update.password.fail"));
		}
	}

	@Override
	public void updateIdCalendrier(Long idUtilisateur, String idCalendrierUtilisateur) throws LambdaException {
		try {
			utilisateurDao.updateIdCalendrier(idUtilisateur, idCalendrierUtilisateur);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.update.calander.fail"));
		}
	}
	
	@Override
	public void updateUrlPhotoUtilisateur(Long idUtilisateur, String urlPhotoUtilisateur) throws LambdaException {
		try {
			utilisateurDao.updateUrlPhotoUtilisateur(idUtilisateur, urlPhotoUtilisateur);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.update.photo.fail"));
		}
	}

	@Override
	public void forgetPassword(Utilisateur utilisateur) throws LambdaException {
		try {
			Utilisateur utilisateur2 = utilisateurDao.findOne(utilisateur.getIdUtilisateur());
			if(utilisateur2.getEmailUtilisateur().equals(utilisateur.getEmailUtilisateur())){
				//Generer un password
				String newPassword = RandomGenerator.randomString();
				//Sauvgarder
				utilisateurDao.updatePassword(newPassword, utilisateur.getIdUtilisateur());
				//Send mail password
				utilisateur2.setPasswordUtilisateur(newPassword);
				mailMetier.sendMailForgetPassword(utilisateur2);
			}else
				throw new LambdaException(PropretiesHelper.getText("utilisateur.update.password.fail"));

		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.update.password.fail"));
		}
	}

	@Override
	public void deleteUtilisateur(Long id)throws LambdaException {
		try {
			utilisateurDao.delete(id);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.delete.fail"));
		}
	}


	@Override
	public Utilisateur getUtilisateur(Long id)  throws LambdaException {
		try {
			return utilisateurDao.findOne(id);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.id.notfound"));
		}
	}


	@Override
	public List<Utilisateur> getAllUtilisateur()throws LambdaException {
		try {
			return Lists.newArrayList(utilisateurDao.findAll());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.list.load.fail "));
		}
	}

	@Override
	public void departCollaborateur(Long id) throws LambdaException {
		try {
			Collaborateur collaborateur = collaborateurDAO.findOne(id);
			collaborateur.setDateDepartCollaborateur(new Date());
			collaborateur.setEtatCollaborateur(false);
			collaborateurDAO.save(collaborateur);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.list.load.fail "));
		}

	}

	//Les collaborateur partir
	@Override
	public List<Collaborateur> getAllOldCollaborateur() throws LambdaException {
		try {
			return collaborateurDAO.findByEtatCollaborateur(false);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.update.fail"));
		}

	}

	@Override
	public List<ManagerRH> getAllManagerRH() throws LambdaException {
		try {
			return Lists.newArrayList(managerRHDAO.findAll());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.list.load.fail"));
		}
	}

	@Override
	public List<Evaluateur> getAllEvaluateur() throws LambdaException {
		try {
			return Lists.newArrayList(evaluateurDAO.findAll());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.list.load.fail"));
		}
	}

	@Override
	public Utilisateur login(String emailUtilisateur, String passwordUtilisateur) throws LambdaException {
		try {
			if(emailUtilisateur==null || passwordUtilisateur==null)
				throw new LambdaException(PropretiesHelper.getText("utilisateur.id.notfound"));
			Utilisateur utilisateur=utilisateurDao.login(emailUtilisateur, passwordUtilisateur);
			if(utilisateur==null)
				throw new LambdaException(PropretiesHelper.getText("utilisateur.login.fail"));
			else
			return utilisateur;
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("utilisateur.login.fail"));
		}
	}


}
