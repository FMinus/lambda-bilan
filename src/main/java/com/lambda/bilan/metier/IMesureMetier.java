package com.lambda.bilan.metier;

import java.util.List;

import com.lambda.bilan.entities.Mesure;
import com.lambda.bilan.entities.ResponsableMesure;
import com.lambda.bilan.helpers.LambdaException;

public interface IMesureMetier {

	public void addMesure(Mesure mesure)  throws LambdaException;
	public void addMesure(List<Mesure> mesures)  throws LambdaException;
	public void updateMesure(Mesure mesure)  throws LambdaException;
	public void deleteMesure(Long id)  throws LambdaException;
	public List<ResponsableMesure> getAllResponsableMesure() throws LambdaException;
	
}
