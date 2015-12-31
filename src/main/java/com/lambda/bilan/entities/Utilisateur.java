package com.lambda.bilan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



@Entity
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name = "ROLE",discriminatorType = DiscriminatorType.STRING )
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"emailUtilisateur"}))
public class Utilisateur implements Serializable {


	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUtilisateur;	
	private String urlPhotoUtilisateur="https://localhost:8443/app/assets/images/avatar.png";
	@JsonIgnore
	private String passwordUtilisateur;
	@NotNull private String telephoneUtilisateur;
	@NotNull private String sexeUtilisateur;
	@NotNull private String nomUtilisateur;
	@NotNull private String prenomUtilisateur;
	@NotNull private String emailUtilisateur;
	@Temporal(TemporalType.DATE)
	@NotNull private Date dateNaissanceUtilisateur;
	@NotNull private String adresseUtilisateur;
	private String idCalendrierUtilisateur;
	
	
	
	public Utilisateur() {
		super();
	}
	public Utilisateur(Long idUtilisateur) {
		this.idUtilisateur=idUtilisateur;
	}
	
	public Utilisateur(Long idUtilisateur, String urlPhotoUtilisateur, String passwordUtilisateur,
			String telephoneUtilisateur, String sexeUtilisateur, String nomUtilisateur, String prenomUtilisateur,
			String emailUtilisateur, Date dateNaissanceUtilisateur, String adresseUtilisateur) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.urlPhotoUtilisateur = urlPhotoUtilisateur;
		this.passwordUtilisateur = passwordUtilisateur;
		this.telephoneUtilisateur = telephoneUtilisateur;
		this.sexeUtilisateur = sexeUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.prenomUtilisateur = prenomUtilisateur;
		this.emailUtilisateur = emailUtilisateur;
		this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
		this.adresseUtilisateur = adresseUtilisateur;
	}


	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	@JsonIgnore
	public String getPasswordUtilisateur() {
		return passwordUtilisateur;
	}
	@JsonProperty
	public void setPasswordUtilisateur(String passwordUtilisateur) {
		this.passwordUtilisateur = passwordUtilisateur;
	}
	public String getTelephoneUtilisateur() {
		return telephoneUtilisateur;
	}
	public void setTelephoneUtilisateur(String telephoneUtilisateur) {
		this.telephoneUtilisateur = telephoneUtilisateur;
	}
	public String getSexeUtilisateur() {
		return sexeUtilisateur;
	}
	public void setSexeUtilisateur(String sexeUtilisateur) {
		this.sexeUtilisateur = sexeUtilisateur;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public String getPrenomUtilisateur() {
		return prenomUtilisateur;
	}
	public void setPrenomUtilisateur(String prenomUtilisateur) {
		this.prenomUtilisateur = prenomUtilisateur;
	}
	public String getEmailUtilisateur() {
		return emailUtilisateur;
	}
	public void setEmailUtilisateur(String emailUtilisateur) {
		this.emailUtilisateur = emailUtilisateur;
	}
	public Date getDateNaissanceUtilisateur() {
		return dateNaissanceUtilisateur;
	}
	public void setDateNaissanceUtilisateur(Date dateNaissanceUtilisateur) {
		this.dateNaissanceUtilisateur = dateNaissanceUtilisateur;
	}
	public String getAdresseUtilisateur() {
		return adresseUtilisateur;
	}
	public void setAdresseUtilisateur(String adresseUtilisateur) {
		this.adresseUtilisateur = adresseUtilisateur;
	}
	public String getUrlPhotoUtilisateur() {
		return urlPhotoUtilisateur;
	}
	public void setUrlPhotoUtilisateur(String urlPhotoUtilisateur) {
		this.urlPhotoUtilisateur = urlPhotoUtilisateur;
	}


	public String getIdCalendrierUtilisateur() {
		return idCalendrierUtilisateur;
	}


	public void setIdCalendrierUtilisateur(String idCalendrierUtilisateur) {
		this.idCalendrierUtilisateur = idCalendrierUtilisateur;
	}
	
	
 
}
