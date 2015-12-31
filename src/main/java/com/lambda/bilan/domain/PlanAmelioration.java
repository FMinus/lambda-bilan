package com.lambda.bilan.domain;

import java.util.List;

import com.lambda.bilan.entities.Action;
import com.lambda.bilan.entities.PlanFormation;

public class PlanAmelioration {
	
	private List<PlanFormation> planFormations; 
	private List<Action> actions;
	
	public PlanAmelioration(List<PlanFormation> planFormations, List<Action> actions) {
		super();
		this.planFormations = planFormations;
		this.actions = actions;
	}
	public PlanAmelioration() {
		super();
		
	}
	public List<PlanFormation> getPlanFormations() {
		return planFormations;
	}

	public void setPlanFormations(List<PlanFormation> planFormations) {
		this.planFormations = planFormations;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
	
	
	
	
	
	
	
}
