package com.lambda.bilan.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.PlanFormation;


public interface PlanFormationDAO extends CrudRepository<PlanFormation, Long> {
	public List<PlanFormation> findByObjectifs_dateCreationObjectifBetweenAndObjectifs_Collaborateur( Date dateDebut,Date dateFin,Collaborateur collaborateur);

	
}
