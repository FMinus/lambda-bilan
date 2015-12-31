package com.lambda.bilan.metier;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.bilan.dao.MesureDAO;
import com.lambda.bilan.dao.ObjectifDAO;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Mesure;
import com.lambda.bilan.entities.Objectif;
import com.lambda.bilan.entities.ResponsableMesure;
import com.lambda.bilan.helpers.DateHelper;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;
import com.lambda.bilan.helpers.StatutObjectif;


@Transactional(rollbackFor= LambdaException.class)
@Service("Mesure")
public class MesureMetier implements IMesureMetier {

	@Autowired
	MesureDAO mesureDAO;
	@Autowired
	ObjectifDAO objectifDAO;

	@Override
	public void addMesure(Mesure mesure) throws LambdaException {
		try {
			Objectif objectif = objectifDAO.findOne( mesure.getObjectif().getIdObjectif());
			Collaborateur collaborateur = objectif.getCollaborateur();
			Date dateBAP =DateHelper.dateBAPthisYear(collaborateur);
			Integer poids=0;
			//AllObjectifsOfCollaborateurThisYear
			List<Objectif> objectifs= objectifDAO.findByCollaborateurAndDateCreationObjectifBetweenAndStatutObjectif(collaborateur, DateHelper.dateSubtractYear(dateBAP), dateBAP,StatutObjectif.VALIDER.toString());
			for (Objectif obj : objectifs) {
				if(obj.getMesures()!=null){
					for (Mesure m : obj.getMesures()) {
						if(m.getPoidsMesure()!=null){
							poids+= m.getPoidsMesure();
						}
						
					}
				}

			}
			if(poids>100)
				throw new LambdaException(PropretiesHelper.getText("mesure.add.fail"));
			else
				mesureDAO.save(mesure);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new LambdaException(PropretiesHelper.getText("mesure.add.fail"));
		}

	}
	
	@Override
	public void addMesure(List<Mesure> mesures) throws LambdaException {
		try {
			System.out.println("add liste");
			mesureDAO.save(mesures);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("mesure.add.fail"));
		}
	}

	@Override
	public void updateMesure(Mesure mesure) throws LambdaException {
		try {
			mesure.setObjectif(mesureDAO.findOne(mesure.getIdMesure()).getObjectif());
			mesureDAO.save(mesure);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("mesure.update.fail"));
		}
	}

	@Override
	public void deleteMesure(Long id) throws LambdaException {
		try {
			mesureDAO.delete(id);
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("mesure.delete.fail"));
		}
	}

	@Override
	public List<ResponsableMesure> getAllResponsableMesure() throws LambdaException {
		try {
			return mesureDAO.getAllResponsableMesure();
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("responsable.list.load.fail"));
		}
	}

}
