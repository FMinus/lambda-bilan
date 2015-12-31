package com.lambda.bilan.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lambda.bilan.entities.Collaborateur;
import com.lambda.bilan.entities.Objectif;


public interface ObjectifDAO extends CrudRepository<Objectif, Long> {
	
	public List<Objectif> findByCollaborateurAndDateCreationObjectifBetweenAndStatutObjectif(Collaborateur collaborateur, Date dateDebut,Date dateFin, String statutObjectif);
	
	@Modifying
    @Query("UPDATE Objectif o SET o.statutObjectif ='validé'  WHERE o.idObjectif = ?1")
	public void validerObjectif(Long id);
	
	@Modifying
    @Query("UPDATE Objectif o SET o.statutObjectif ='refusé' , o.compteurRejet=?1 WHERE o.idObjectif = ?2")
	public void refuserObjectif(Integer compteurRejet,Long id);
	
	@Modifying
    @Query("UPDATE Objectif o SET o.nomObjectif =?2 , o.descriptifObjectif=?3 , o.idCategorie=?4 , o.statutObjectif='validé' WHERE o.idObjectif = ?1")
	public void updateObjectif(Long idObjectif , String nomObjectif, String descriptifObjectif, Long idCategorie);
	
	@Query("select o from Objectif o where o.statutObjectif='refusé' and o.collaborateur in (select c from Collaborateur c where month(c.dateEmbaucheCollaborateur) = month(current_date()) and c.idManagerRH = ?1)")  
	public List<Objectif> getAllObjectifsRefusFromCollaborateurOfManagerRH(Long id);
}
