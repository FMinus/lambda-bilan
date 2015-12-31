package com.lambda.bilan.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Action implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAction;
	@Temporal(TemporalType.DATE)
	private Date dateAction=new Date();
	private String messageAction;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCollaborateur")
	@JsonIgnore
	private Collaborateur collaborateur;
	
	//clé étrangère
	@Column(name = "idCollaborateur", insertable = false, updatable = false)
	private Long idCollaborateur;
	
	public Action() {
		super();
		// TODO Auto-generated constructor stub
	}
	@JsonIgnore
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}
	@JsonProperty
	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	public Long getIdCollaborateur() {
		return idCollaborateur;
	}
	public void setIdCollaborateur(Long idCollaborateur) {
		this.idCollaborateur = idCollaborateur;
	}
	private String objetAction;
	
	
	public Long getIdAction() {
		return idAction;
	}
	public void setIdAction(Long idAction) {
		this.idAction = idAction;
	}
	public Date getDateAction() {
		return dateAction;
	}
	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}
	public String getMessageAction() {
		return messageAction;
	}
	public void setMessageAction(String messageAction) {
		this.messageAction = messageAction;
	}
	public String getObjetAction() {
		return objetAction;
	}
	public void setObjetAction(String objetAction) {
		this.objetAction = objetAction;
	}
	
	

}
