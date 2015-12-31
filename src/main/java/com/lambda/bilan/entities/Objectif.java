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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Objectif implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idObjectif;
	@Temporal(TemporalType.DATE)
	private Date dateCreationObjectif=new Date();
	private String nomObjectif;
	private String descriptifObjectif;
	private String statutObjectif="validé";
	private Integer compteurRejet=0;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="idObjectif")
	private List<Mesure> mesures;
	
	public Objectif(Long idObjectif, String nomObjectif, List<Mesure> mesures) {
		super();
		this.idObjectif = idObjectif;
		this.nomObjectif = nomObjectif;
		this.mesures = mesures;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCollaborateur")
	private Collaborateur collaborateur;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategorie")
	private Categorie categorie;
	
	
	public List<Mesure> getMesures() {
		return mesures;
	}
	public void setMesures(List<Mesure> mesures) {
		this.mesures = mesures;
	}
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public Long getIdCollaborateur() {
		return idCollaborateur;
	}
	public void setIdCollaborateur(Long idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}
	public Long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}

	
	//clés étrangères
	@Column(name = "idCollaborateur", insertable = false, updatable = false)
	private Long idCollaborateur;
	
	@Column(name = "idCategorie", insertable = false, updatable = false)
	private Long idCategorie;
	
	public Objectif() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdObjectif() {
		return idObjectif;
	}
	public void setIdObjectif(Long idObjectif) {
		this.idObjectif = idObjectif;
	}
	public Date getDateCreationObjectif() {
		return dateCreationObjectif;
	}
	public void setDateCreationObjectif(Date dateCreationObjectif) {
		this.dateCreationObjectif = dateCreationObjectif;
	}
	public String getNomObjectif() {
		return nomObjectif;
	}
	public void setNomObjectif(String nomObjectif) {
		this.nomObjectif = nomObjectif;
	}
	public String getStatutObjectif() {
		return statutObjectif;
	}
	public void setStatutObjectif(String statutObjectif) {
		this.statutObjectif = statutObjectif;
	}
	public String getDescriptifObjectif() {
		return descriptifObjectif;
	}
	public void setDescriptifObjectif(String descriptifObjectif) {
		this.descriptifObjectif = descriptifObjectif;
	}
	public Integer getCompteurRejet() {
		return compteurRejet;
	}
	public void setCompteurRejet(Integer compteurRejet) {
		this.compteurRejet = compteurRejet;
	}
	

}
