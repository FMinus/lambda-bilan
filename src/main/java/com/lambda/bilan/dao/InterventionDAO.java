package com.lambda.bilan.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Intervention;

public interface InterventionDAO extends CrudRepository<Intervention, Long> {
	
	public List<Intervention> findByDateFinInterventionBetweenAndCollaborateur( Date dateDebut,Date dateFin,Collaborateur collaborateur);
	public List<Intervention> findTop6ByProjet_IdEvaluateurAndDateFinInterventionLessThanOrderByDateFinInterventionDesc(Long id,Date date);
	public Intervention findOneByIdProjetAndIdCollaborateur(Long idProjet, Long idCollaborateur);
	@Modifying
    @Query("UPDATE Intervention i SET i.etatIntervention = true  WHERE i.idIntervention = ?1")
	public void validerFeedback(Long idIntervention);
}
