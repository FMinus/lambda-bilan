package com.lambda.bilan.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( "ADMINISTRATEUR" )
public class Administrateur extends Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
