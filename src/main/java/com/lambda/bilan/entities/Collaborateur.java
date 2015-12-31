package com.lambda.bilan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@DiscriminatorValue( "COLLABORATEUR" )
public class Collaborateur extends Utilisateur implements Serializable{

	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date dateEmbaucheCollaborateur;
	@Temporal(TemporalType.DATE)
	private Date dateDepartCollaborateur;
	private String posteActuelCollaborateur;
	private Boolean etatCollaborateur=true;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idManagerRH")
	@JsonIgnore
	@NotNull private ManagerRH managerRH;

	// clé étrangère
	@Column(name = "idManagerRH", insertable = false, updatable = false)
	private Long idManagerRH;
	public Collaborateur() {
		super();
	}
	public Collaborateur(Long id) {
		super(id);
		this.etatCollaborateur=true;
	}
	public Date getDateEmbaucheCollaborateur() {
		return dateEmbaucheCollaborateur;
	}
	public void setDateEmbaucheCollaborateur(Date dateEmbaucheCollaborateur) {
		this.dateEmbaucheCollaborateur = dateEmbaucheCollaborateur;
	}
	public String getPosteActuelCollaborateur() {
		return posteActuelCollaborateur;
	}
	public void setPosteActuelCollaborateur(String posteActuelCollaborateur) {
		this.posteActuelCollaborateur = posteActuelCollaborateur;
	}
	public Boolean getEtatCollaborateur() {
		return etatCollaborateur;
	}
	public void setEtatCollaborateur(Boolean etatCollaborateur) {
		this.etatCollaborateur = etatCollaborateur;
	}
	@JsonIgnore
	public ManagerRH getManagerRH() {
		return managerRH;
	}
	@JsonProperty
	public void setManagerRH(ManagerRH managerRH) {
		this.managerRH = managerRH;
	}
	public Date getDateDepartCollaborateur() {
		return dateDepartCollaborateur;
	}
	public void setDateDepartCollaborateur(Date dateDepartCollaborateur) {
		this.dateDepartCollaborateur = dateDepartCollaborateur;
	}
	public Long getIdManagerRH() {
		return idManagerRH;
	}
	public void setIdManagerRH(Long idManagerRH) {
		this.idManagerRH = idManagerRH;
	}
	


}
