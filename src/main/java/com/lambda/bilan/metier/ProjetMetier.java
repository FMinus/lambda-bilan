package com.lambda.bilan.metier;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lambda.bilan.dao.ProjetDAO;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;


@Service("Projet")
@Transactional(rollbackFor= LambdaException.class)
public class ProjetMetier implements IProjetMetier {
	
	@Autowired
	private ProjetDAO projetDAO;
	
	
	@Override
	public List<Projet> getAllProjet() throws LambdaException {
		try {
			return Lists.newArrayList(projetDAO.findAll());
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.list.load.fail"));
		}
		
	}

	@Override
	public void addProjet(Projet projet) throws LambdaException {
		try {
			projetDAO.save(projet);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.add.fail"));
		}
	}

	@Override
	public void updateProjet(Projet projet) throws LambdaException{
		try {
			if(projet.getIdProjet()==null)
				throw new LambdaException(PropretiesHelper.getText("projet.update.fail"));
			projetDAO.save(projet);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.update.fail"));
		}
		
	}

	@Override
	public void deleteProjet(Long id) throws LambdaException {
		try {
			projetDAO.delete(id);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.delete.fail"));
		}
	}

	@Override
	public Projet getProjet(Long id) throws LambdaException {
		try {
			return projetDAO.findOne(id);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.id.notfound"));
		}
	}

	@Override
	public List<Projet> getAllProjetOfEvaluateur(Evaluateur evaluateur) throws LambdaException{
		try {
			return projetDAO.findAllByEvaluateur(evaluateur);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.list.load.fail"));
		}
	}

	@Override
	public List<Projet> getAllProjetOfCollaborateur(Collaborateur collaborateur) throws LambdaException {
		try {
			return projetDAO.getAllProjetOfCollaborateur(collaborateur);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("projet.list.load.fail"));
		}
	}

}
