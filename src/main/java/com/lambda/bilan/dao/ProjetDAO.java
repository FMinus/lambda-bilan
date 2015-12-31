package com.lambda.bilan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Evaluateur;
import com.lambda.bilan.entities.Projet;
import com.lambda.bilan.helpers.LambdaException;


public interface ProjetDAO extends CrudRepository<Projet, Long> {
	public List<Projet> findAllByEvaluateur(Evaluateur evaluateur)  throws LambdaException;
	
	@Query("select p from Projet p where p in (select i.projet from Intervention i where i.dateFinIntervention >current_date() and i.collaborateur = ?1)")  
	public List<Projet> getAllProjetOfCollaborateur(Collaborateur collaborateur);
}
