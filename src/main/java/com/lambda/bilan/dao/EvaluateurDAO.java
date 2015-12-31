package com.lambda.bilan.dao;

import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Evaluateur;


public interface EvaluateurDAO extends CrudRepository<Evaluateur, Long> {
	
}
