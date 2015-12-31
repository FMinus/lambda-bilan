package com.lambda.bilan.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lambda.bilan.dao.CollaborateurDAO;
import com.lambda.bilan.domain.BAP;
import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.helpers.DateHelper;
import com.lambda.bilan.helpers.LambdaException;
import com.lambda.bilan.helpers.PropretiesHelper;

@Transactional(rollbackFor= LambdaException.class)
@Service("BAPMetier")
public class BAPMetier implements IBAPMetier {

	private static final String STATUT_EN_ATTENT = "En attente";
	private static final String STATUT_EN_COURS = "En cours";
	private static final String STATUT_VALIDE = "Validé";
	private static final String STATUT_REJETE = "Rejeté";
	private static final String STATUT_ANULER = "Anuller";

	@Autowired
	private CollaborateurDAO collaborateurDAO;


	@Override
	public List<BAP> getAllBAPOfCollaborateur(Long idCollaborateur) throws LambdaException {
		try {
			BAP bap=null;
			List<BAP> listBAP=new ArrayList<BAP>();
			Collaborateur collaborateur= collaborateurDAO.findOne(idCollaborateur);
			Date dateEmbauche =collaborateur.getDateEmbaucheCollaborateur();
			Date dateBAPThisYear =DateHelper.dateBAPthisYear(collaborateur);
			Date date=DateHelper.dateAddtYear(dateEmbauche);
			if(collaborateur.getEtatCollaborateur()==true){
				while(date.before(dateBAPThisYear)){
					bap=new BAP(new java.sql.Date(date.getTime()).toString(),STATUT_VALIDE);
					listBAP.add(bap);
					date=DateHelper.dateAddtYear(date);
				}
				Date datenow = new Date();
				
				if(datenow.before(DateHelper.dateSubtract11Month(date)))
					listBAP.add(new BAP(new java.sql.Date(date.getTime()).toString(),STATUT_EN_ATTENT));
				else
					listBAP.add(new BAP(new java.sql.Date(date.getTime()).toString(),STATUT_EN_COURS));
			}
			if(collaborateur.getEtatCollaborateur()==false && collaborateur.getDateDepartCollaborateur()!=null){
				while(date.before(dateBAPThisYear) && date.before(collaborateur.getDateDepartCollaborateur())){
					bap=new BAP(new java.sql.Date(date.getTime()).toString(),STATUT_VALIDE);
					listBAP.add(bap);
					date=DateHelper.dateAddtYear(date);
				}
				listBAP.add(new BAP(new java.sql.Date(date.getTime()).toString(),STATUT_ANULER));
			}
			return listBAP;
		} catch (Exception e) {
			throw new LambdaException(PropretiesHelper.getText("general.list.load.fail"));
		}
	}

}
