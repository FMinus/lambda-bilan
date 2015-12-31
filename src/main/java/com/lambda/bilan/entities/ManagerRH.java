package com.lambda.bilan.entities;

import java.io.Serializable;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue( "MANAGER_RH" )
public class ManagerRH extends Utilisateur implements Serializable{
	
	private static final long serialVersionUID = 1L;
	


	public ManagerRH() {
		super();
		// TODO Auto-generated constructor stub
	}

}
