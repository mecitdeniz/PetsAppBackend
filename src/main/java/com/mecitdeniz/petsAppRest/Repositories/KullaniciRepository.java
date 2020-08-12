package com.mecitdeniz.petsAppRest.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mecitdeniz.petsAppRest.Entities.Kullanici;

public interface KullaniciRepository extends JpaRepository<Kullanici, Integer>{

	
	public default List<Kullanici> getAllKullanici(){
		return this.findAll();
	}
	public default Optional<Kullanici> idIleKullaniciGetir(int id) {
		Optional<Kullanici> kullanici = this.findById(id);
		return kullanici;
	}
	
	public default void addKullanici(Kullanici kullanici) {
		this.save(kullanici);
	}
	
	public default void kullaniciGuncelle(int id,Kullanici kullanici) {
		this.save(kullanici);
	}

	public default void deleteKullanici(int id) {
		this.deleteById(id);
	}
	
	
	@Query("SELECT k FROM Kullanici k WHERE k.email = ?1")
	public Optional<Kullanici> emailKayitliMi(String email);
	
	@Query("SELECT k FROM Kullanici k WHERE k.kullaniciAdi = ?1")
	public Optional<Kullanici> kullaniciAdiKayitliMi(String kullaniciAdi);
	
}
