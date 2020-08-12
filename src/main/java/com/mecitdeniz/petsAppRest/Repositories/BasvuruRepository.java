package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Basvuru;

public interface BasvuruRepository extends JpaRepository<Basvuru, Integer>{

	
	
	@Query("SELECT b FROM Basvuru b WHERE b.ilanId = ?1 and (b.durumId = 1 or b.durumId = 2)")
	List<Basvuru> ilanIdIleBasvurulariGetir(int ilanId);
	
	@Query("SELECT b FROM Basvuru b WHERE b.ilanId = ?1 and b.durumId = 2")
	Optional<Basvuru> ilanIdIleBasvuruGetir(int ilanId);

	
	@Query("SELECT b FROM Basvuru b WHERE b.basvuranId = ?1")
	List<Basvuru> kullaniciIdIleIlanGetir(int kullaniciId);


}
