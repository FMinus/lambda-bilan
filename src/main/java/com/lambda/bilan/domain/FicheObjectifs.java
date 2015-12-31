package com.lambda.bilan.domain;

import java.util.List;

import com.lambda.bilan.entities.Objectif;

public class FicheObjectifs {

	private List<Objectif> objectifs;
	private Float noteFinal;
	
	public FicheObjectifs() {
		super();
	}
	
	public FicheObjectifs(List<Objectif> objectifs, Float noteFinal) {
		super();
		this.objectifs = objectifs;
		this.noteFinal = noteFinal;
	}
	
	public List<Objectif> getObjectifs() {
		return objectifs;
	}
	public void setObjectifs(List<Objectif> objectifs) {
		this.objectifs = objectifs;
	}
	public Float getNoteFinal() {
		return noteFinal;
	}
	public void setNoteFinal(Float noteFinal) {
		this.noteFinal = noteFinal;
	}
	
	
}
