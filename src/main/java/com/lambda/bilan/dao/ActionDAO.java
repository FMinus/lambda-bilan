package com.lambda.bilan.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Action;
import com.lambda.bilan.entities.Collaborateur;

public interface ActionDAO extends CrudRepository<Action, Long> {
	
	public List<Action> findByDateActionBetweenAndCollaborateur( Date dateDebut,Date dateFin,Collaborateur collaborateur) ;

}
