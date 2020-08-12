package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Ilan;;

public interface IlanRepository  extends JpaRepository<Ilan, Integer>{

	
	@Query("SELECT i FROM Ilan i WHERE i.yayinlayanId = ?1")
	public List<Ilan> kullaniciIdIleGetir(int kullaniciId);

	
	@Query("SELECT i FROM Ilan i WHERE i.id = ?1")
	public Ilan ilanGetirIdIle(int id);

	
	@Query("SELECT i FROM Ilan i WHERE i.durumId = ?1")
	public List<Ilan> ilanlariGetir(int durumId);

}
