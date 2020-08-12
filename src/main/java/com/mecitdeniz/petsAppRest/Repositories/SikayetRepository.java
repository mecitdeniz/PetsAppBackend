package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Sikayet;

public interface SikayetRepository extends JpaRepository<Sikayet, Integer> {

	
	@Query("SELECT s FROM Sikayet s WHERE s.ilanId = ?1")
	List<Sikayet> ilanIdIleSikayetGetir(int ilanId);

}
