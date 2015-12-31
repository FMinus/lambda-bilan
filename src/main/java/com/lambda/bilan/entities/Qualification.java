package com.lambda.bilan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Qualification implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long idQualification;
	private String intituleQualification;
	private Integer poidsQualification;
	
	
	public Qualification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdQualification() {
		return idQualification;
	}
	public void setIdQualification(Long idQualification) {
		this.idQualification = idQualification;
	}
	public String getIntituleQualification() {
		return intituleQualification;
	}
	public void setIntituleQualification(String intituleQualification) {
		this.intituleQualification = intituleQualification;
	}
	public Integer getPoidsQualification() {
		return poidsQualification;
	}
	public void setPoidsQualification(Integer poidsQualification) {
		this.poidsQualification = poidsQualification;
	}
	
	

}
