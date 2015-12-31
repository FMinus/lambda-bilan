package com.lambda.bilan.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Mesure implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMesure;
	private String commentMesurer;
	private Integer poidsMesure;
	private Integer resultatMesure;
	private Boolean modeAccesMesure;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idObjectif")
	@JsonIgnore
	private Objectif objectif;
	
	
	public Mesure(Long idMesure, String commentMesurer) {
		super();
		this.idMesure = idMesure;
		this.commentMesurer = commentMesurer;
	}
	public ResponsableMesure getResponsableMesure() {
		return responsableMesure;
	}
	public void setResponsableMesure(ResponsableMesure responsableMesure) {
		this.responsableMesure = responsableMesure;
	}
	public Long getIdObjectif() {
		return idObjectif;
	}
	public void setIdObjectif(Long idObjectif) {
		this.idObjectif = idObjectif;
	}
	/*public Long getIdResponsableMesure() {
		return idResponsableMesure;
	}
	public void setIdResponsableMesure(Long idResponsableMesure) {
		this.idResponsableMesure = idResponsableMesure;
	}*/
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "idResponsableMesure" )
	private ResponsableMesure responsableMesure;
	
	
	//clé étrangères
	@Column(name = "idObjectif", insertable = false, updatable = false)
	private Long idObjectif;
	/*
	@Column(name = "idResponsableMesure", insertable = false, updatable = false)
	private Long idResponsableMesure;
	*/
	
	public Mesure() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdMesure() {
		return idMesure;
	}
	public void setIdMesure(Long idMesure) {
		this.idMesure = idMesure;
	}
	public String getCommentMesurer() {
		return commentMesurer;
	}
	public void setCommentMesurer(String commentMesurer) {
		this.commentMesurer = commentMesurer;
	}
	public Integer getPoidsMesure() {
		return poidsMesure;
	}
	public void setPoidsMesure(Integer poidsMesure) {
		this.poidsMesure = poidsMesure;
	}
	public Integer getResultatMesure() {
		return resultatMesure;
	}
	public void setResultatMesure(Integer resultatMesure) {
		this.resultatMesure = resultatMesure;
	}
	public Boolean getModeAccesMesure() {
		return modeAccesMesure;
	}
	public void setModeAccesMesure(Boolean modeAccesMesure) {
		this.modeAccesMesure = modeAccesMesure;
	}
	@JsonIgnore
	public Objectif getObjectif() {
		return objectif;
	}
	@JsonProperty
	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
	}

	
}
