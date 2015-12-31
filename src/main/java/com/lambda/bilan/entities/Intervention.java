package com.lambda.bilan.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"idProjet", "idCollaborateur"}))
public class Intervention implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idIntervention;
	@Temporal(TemporalType.DATE)
	private Date dateDebutIntervention;
	@Temporal(TemporalType.DATE)
	private Date dateFinIntervention;
	private Integer nombreJoursValorises;
	private String nomIntervention ="sans nom";
	private String remarqueIntervention="sans remarque";
	private String roleJoue;
	private Boolean etatIntervention= false;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="idIntervention")
	private List<Note> notes;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProjet")
	@JsonIgnore
	private Projet projet;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCollaborateur")
	@JsonIgnore
	private Collaborateur collaborateur;
	
	//clé étrangères
	@Column(name = "idProjet", insertable = false, updatable = false)
	private Long idProjet;
	@Column(name = "idCollaborateur", insertable = false, updatable = false)
	private Long idCollaborateur;
	
	
	public Intervention() {
		super();
		this.etatIntervention=false;
	}
	public Long getIdIntervention() {
		return idIntervention;
	}
	public void setIdIntervention(Long idIntervention) {
		this.idIntervention = idIntervention;
	}
	public Date getDateDebutIntervention() {
		return dateDebutIntervention;
	}
	public void setDateDebutIntervention(Date dateDebutIntervention) {
		this.dateDebutIntervention = dateDebutIntervention;
	}
	public Date getDateFinIntervention() {
		return dateFinIntervention;
	}
	public void setDateFinIntervention(Date dateFinIntervention) {
		this.dateFinIntervention = dateFinIntervention;
	}
	public Integer getNombreJoursValorises() {
		return nombreJoursValorises;
	}
	public void setNombreJoursValorises(Integer nombreJoursValorises) {
		this.nombreJoursValorises = nombreJoursValorises;
	}
	public String getNomIntervention() {
		return nomIntervention;
	}
	public void setNomIntervention(String nomIntervention) {
		this.nomIntervention = nomIntervention;
	}
	public String getRemarqueIntervention() {
		return remarqueIntervention;
	}
	public void setRemarqueIntervention(String remarqueIntervention) {
		this.remarqueIntervention = remarqueIntervention;
	}
	public String getRoleJoue() {
		return roleJoue;
	}
	public void setRoleJoue(String roleJoue) {
		this.roleJoue = roleJoue;
	}
	@JsonIgnore
	public Projet getProjet() {
		return projet;
	}
	@JsonProperty
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	@JsonIgnore
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}
	@JsonProperty
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	public Boolean getEtatIntervention() {
		return etatIntervention;
	}
	public void setEtatIntervention(Boolean etatIntervention) {
		this.etatIntervention = etatIntervention;
	}
	public List<Note> getNotes() {
		return notes;
	}
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	public Long getIdCollaborateur() {
		return idCollaborateur;
	}
	public void setIdCollaborateur(Long idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}
	
	
	
	
	

}
