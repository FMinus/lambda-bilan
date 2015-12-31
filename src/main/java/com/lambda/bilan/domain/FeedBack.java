package com.lambda.bilan.domain;

import com.lambda.bilan.entities.Intervention;

public class FeedBack {
	
	private Intervention intervention;
	private Integer nbrThemeQualifies;
	private Integer totalePoids;
	private long noteGlobale;
	
	public FeedBack(Intervention intervention, Integer nbrThemeQualifies, Integer totalePoids,
			long noteGlobale) {
		super();
		this.intervention = intervention;
		this.nbrThemeQualifies = nbrThemeQualifies;
		this.totalePoids = totalePoids;
		this.noteGlobale = noteGlobale;
	}
	
	public FeedBack() {
		super();
	}
	public Intervention getIntervention() {
		return intervention;
	}
	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}
	public Integer getNbrThemeQualifies() {
		return nbrThemeQualifies;
	}
	public void setNbrThemeQualifies(Integer nbrThemeQualifies) {
		this.nbrThemeQualifies = nbrThemeQualifies;
	}
	public Integer getTotalePoids() {
		return totalePoids;
	}
	public void setTotalePoids(Integer totalePoids) {
		this.totalePoids = totalePoids;
	}
	public long getNoteGlobale() {
		return noteGlobale;
	}
	public void setNoteGlobale(long noteGlobale) {
		this.noteGlobale = noteGlobale;
	}


	
}
