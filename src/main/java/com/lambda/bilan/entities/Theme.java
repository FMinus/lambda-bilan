package com.lambda.bilan.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Theme implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTheme;
	private String intituleTheme;
	public Theme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdTheme() {
		return idTheme;
	}
	public void setIdTheme(Long idTheme) {
		this.idTheme = idTheme;
	}
	public String getIntituleTheme() {
		return intituleTheme;
	}
	public void setIntituleTheme(String intituleTheme) {
		this.intituleTheme = intituleTheme;
	}
	
	

}
