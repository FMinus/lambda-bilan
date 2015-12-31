/**
 * 
 */
package com.lambda.bilan.helpers;

import java.util.ArrayList;
import java.util.List;

public class EmailModel {
	private List<String> models = new ArrayList<String>();

	public List<String> getModels() {
		return models;
	}

	public void setModels(List<String> models) {
		this.models = models;
	}

	public void addModel(String model){
		models.add(model);
	}
}
