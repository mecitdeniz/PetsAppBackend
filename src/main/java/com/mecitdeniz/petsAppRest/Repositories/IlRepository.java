package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Il;

public interface IlRepository extends JpaRepository<Il, Integer> {
	
	
	
	@Query("SELECT i FROM Il i WHERE i.id = ?1")
	List<Il> idIleIlleriGetir(int ilId);


}
