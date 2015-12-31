package com.lambda.bilan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"idIntervention", "idTheme"}))
public class Note implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idNote;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "idTheme" )
	private Theme theme;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "idQualification" )
	private Qualification qualification;
	

	private String remarqueNote;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idIntervention")
	@JsonIgnore
	private Intervention intervention;
	
	// clés étrangères
	/*@Column(name = "idTheme", insertable = false, updatable = false)
	private Long idTheme;
	
	@Column(name = "idQualification", insertable = false, updatable = false)
	private Long idQualification;
	
	@Column(name = "idIntervention", insertable = false, updatable = false)
	private Long idIntervention;*/
	
	
	
	
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getIdNote() {
		return idNote;
	}

	public void setIdNote(Long idNote) {
		this.idNote = idNote;
	}

	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public Qualification getQualification() {
		return qualification;
	}
	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	public String getRemarqueNote() {
		return remarqueNote;
	}
	public void setRemarqueNote(String remarqueNote) {
		this.remarqueNote = remarqueNote;
	}
/*	public Long getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}
	public Long getIdQualification() {
		return idQualification;
	}
	public void setIdQualification(Long idQualification) {
		this.idQualification = idQualification;
	}
	public Long getIdIntervention() {
		return idIntervention;
	}

	public void setIdIntervention(Long idIntervention) {
		this.idIntervention = idIntervention;
	}
	*/

	@JsonIgnore
	public Intervention getIntervention() {
		return intervention;
	}
	@JsonProperty
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	

	
}
