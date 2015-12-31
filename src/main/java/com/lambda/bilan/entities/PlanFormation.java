package com.lambda.bilan.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class PlanFormation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPlanFormation;
	private Integer dureePlanFormation;
	
	public List<Objectif> getObjectifs() {
		return objectifs;
	}
	public void setObjectifs(List<Objectif> objectifs) {
		this.objectifs = objectifs;
	}
	private String moyensPlanFormation;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="objectif_planFormation",
	joinColumns={@JoinColumn(name="idPlanFormation")},
	inverseJoinColumns={@JoinColumn(name="idObjectif")})
	private List<Objectif> objectifs;
	
	public PlanFormation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdPlanFormation() {
		return idPlanFormation;
	}
	public void setIdPlanFormation(Long idPlanFormation) {
		this.idPlanFormation = idPlanFormation;
	}
	public Integer getDureePlanFormation() {
		return dureePlanFormation;
	}
	public void setDureePlanFormation(Integer dureePlanFormation) {
		this.dureePlanFormation = dureePlanFormation;
	}
	public String getMoyensPlanFormation() {
		return moyensPlanFormation;
	}
	public void setMoyensPlanFormation(String moyensPlanFormation) {
		this.moyensPlanFormation = moyensPlanFormation;
	}
	

}
