package com.lambda.bilan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ResponsableMesure implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idResponsableMesure;
	private String nomResponsableMesure;
	
	public ResponsableMesure() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdResponsableMesure() {
		return idResponsableMesure;
	}
	public void setIdResponsableMesure(Long idResponsableMesure) {
		this.idResponsableMesure = idResponsableMesure;
	}
	public String getNomResponsableMesure() {
		return nomResponsableMesure;
	}
	public void setNomResponsableMesure(String nomResponsableMesure) {
		this.nomResponsableMesure = nomResponsableMesure;
	}
	
	

}
