package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Yorum;

public interface YorumRepository extends JpaRepository<Yorum, Integer> {

	
	@Query("SELECT y FROM Yorum y WHERE y.yorumlananId = ?1")
	List<Yorum> kullaniciIdIleYorumlariGetir(int kullaniciId);

}
