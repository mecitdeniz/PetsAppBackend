package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Hayvan;

public interface HayvanRepository extends JpaRepository<Hayvan, Integer> {

	public default void addHayvan(Hayvan hayvan) {
		this.save(hayvan);
	}

	@Query("SELECT h FROM Hayvan h WHERE h.id = ?1")
	public Hayvan getHayvan(int id);
	
	
	@Query("SELECT h FROM Hayvan h WHERE h.sahipId = ?1")
	public List<Hayvan> getHayvanlarByKullaniciId(int id);

	
	
	
}
