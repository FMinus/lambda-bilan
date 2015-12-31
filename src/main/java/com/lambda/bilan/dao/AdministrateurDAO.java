package com.lambda.bilan.dao;

import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Administrateur;


public interface AdministrateurDAO extends CrudRepository<Administrateur, Long> {
	
}
