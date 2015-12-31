package com.lambda.bilan.helpers;

public enum StatutObjectif {
	VALIDER("validé"),
	REFUSER("refusé");

	private String statut = "";
	
	StatutObjectif(String statut){
		this.statut = statut;
	}

	public String toString(){
		return statut;
	}

}
