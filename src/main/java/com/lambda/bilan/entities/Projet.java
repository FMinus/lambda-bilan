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
import javax.validation.constraints.NotNull;

@Entity
public class Projet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjet;
	private String nomProjet;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEvaluateur")
	@NotNull private Evaluateur evaluateur;

	// clé étrangère
	@Column(name = "idEvaluateur", insertable = false, updatable = false)
	private Long idEvaluateur;


	public Evaluateur getEvaluateur() {
		return evaluateur;
	}
	public void setEvaluateur(Evaluateur evaluateur) {
		this.evaluateur = evaluateur;
	}

	public Long getIdEvaluateur() {
		return idEvaluateur;
	}
	public void setIdEvaluateur(Long idEvaluateur) {
		this.idEvaluateur = idEvaluateur;
	}
	public Projet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public String getNomProjet() {
		return nomProjet;
	}
	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}

}
