package com.mecitdeniz.petsAppRest.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Kullanici;
import com.mecitdeniz.petsAppRest.Repositories.KullaniciRepository;


@RestController
public class GirisCotroller {

	
	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	@PostMapping("/giris")
	private Optional<Kullanici> girisYap(@RequestParam(value = "email") String email,
			@RequestParam(value = "sifre") String sifre){
	
		Optional<Kullanici> kullanici = kullaniciRepository.emailKayitliMi(email);
		System.out.println("sifre"+kullanici.get().getSifre()+"parametre :"+sifre);
		
		String kullaniciSifre = kullanici.get().getSifre();
	
		if(kullaniciSifre.equals(sifre)) { 
			return kullanici;
		}else {
			kullanici = null;
			return kullanici;
		}
		
	}
	
	
	@PostMapping("/kaydol")
	private ResponseEntity<Integer> kayıtOl(@RequestBody Kullanici kullanici){
		
		System.out.println("Gelen Bilgi :" + kullanici.toString());
		String email = kullanici.getEmail();
		String kullaniciAdi = kullanici.getKullaniciAdi();
		
		Optional<Kullanici> k1 = kullaniciRepository.emailKayitliMi(email);
		Optional<Kullanici> k2 = kullaniciRepository.kullaniciAdiKayitliMi(kullaniciAdi);
		
		if(!k1.isPresent() && !k2.isPresent()) {
			kullaniciRepository.save(kullanici);
			k1 = kullaniciRepository.emailKayitliMi(email);
			int id = k1.get().getId();
			return new ResponseEntity<Integer>(id,HttpStatus.CREATED);
		}else if(k1.isPresent()) {
			return new ResponseEntity<Integer>(0,HttpStatus.FOUND);
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.CONFLICT);
		}

		
	}
	
}
