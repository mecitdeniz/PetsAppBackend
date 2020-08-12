package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Mesaj;

public interface MesajRepository extends JpaRepository<Mesaj, Integer>{

	@Query("SELECT m FROM Mesaj m WHERE m.basvuruId = ?1")
	List<Mesaj> basvuruIdIleMesajlariGetir(int basvuruId);

}
