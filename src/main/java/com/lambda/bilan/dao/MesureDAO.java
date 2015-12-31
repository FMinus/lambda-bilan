package com.lambda.bilan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Mesure;
import com.lambda.bilan.entities.ResponsableMesure;

public interface MesureDAO extends CrudRepository<Mesure,Long> {

	@Query("select r from ResponsableMesure r")  
	public List<ResponsableMesure> getAllResponsableMesure() ; 
}
