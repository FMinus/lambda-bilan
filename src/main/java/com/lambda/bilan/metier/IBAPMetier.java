package com.lambda.bilan.metier;

import java.util.List;

import com.lambda.bilan.domain.BAP;
import com.lambda.bilan.helpers.LambdaException;

public interface IBAPMetier {
	
	public List<BAP> getAllBAPOfCollaborateur(Long idCollaborateur) throws LambdaException;
}
