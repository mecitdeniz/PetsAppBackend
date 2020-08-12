package com.mecitdeniz.petsAppRest.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Kullanici;
import com.mecitdeniz.petsAppRest.Repositories.KullaniciRepository;


@RestController
public class KullaniciController {

	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	@GetMapping("/kullanicilar")
	private List<Kullanici> getAllKullanici(){
		return kullaniciRepository.getAllKullanici();
	}
	
	@GetMapping("/kullanicilar/{id}")
	public Optional<Kullanici> idIleKullaniciGetir(@PathVariable int id) {
		return kullaniciRepository.idIleKullaniciGetir(id);
	}
	
	@GetMapping("/kullanicilar/{email}/denetle")
	private Kullanici emailKayitliMi(@PathVariable("email") String email){
		
		Optional<Kullanici> kullanici = kullaniciRepository.emailKayitliMi(email);
		if(kullanici.isPresent()) {
			return kullanici.get();	
		}else {
			return null;
		}
	}
	
	@PutMapping("/kullanicilar/{kullaniciId}/sifreYenile")
	private ResponseEntity<Integer> sifreYenile(@PathVariable("kullaniciId") int kullaniciId,
			@RequestBody Kullanici kullanici){
		
		Optional<Kullanici> k = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
		
		if(k.isPresent()) {
			try {
				k.get().setSifre(kullanici.getSifre());
				kullaniciRepository.save(k.get());
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PostMapping("/kullanicilar")
	public void addKullanic(@RequestBody Kullanici kullanici) {
		kullaniciRepository.addKullanici(kullanici);
	}
	
	@PutMapping("/kullanicilar/{id}")
	public ResponseEntity<Integer> updateKullanici(@PathVariable int id,@RequestBody Kullanici kullanici) {
		
		Optional<Kullanici> k = kullaniciRepository.idIleKullaniciGetir(kullanici.getId());
		
		if(k.isPresent() && k.get().getId() == id) {
			
			try {
				if(!k.get().getKullaniciAdi().equals(kullanici.getKullaniciAdi())) {
					
					Optional<Kullanici> kullaniciAdi = kullaniciRepository.kullaniciAdiKayitliMi(kullanici.getKullaniciAdi());
					if(kullaniciAdi.isPresent()) {
						return new ResponseEntity<Integer>(-1,HttpStatus.CONFLICT);
					}else {
						k.get().setKullaniciAdi(kullanici.getKullaniciAdi());
					}
				}
				k.get().setAd(kullanici.getAd());
				k.get().setSoyAd(kullanici.getSoyAd());
				k.get().setProfilResmi(kullanici.getProfilResmi());
				k.get().setBio(kullanici.getBio());
				kullaniciRepository.kullaniciGuncelle(k.get().getId(),k.get());
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.NOT_FOUND);
		}
	
		
		
	}
	
	@DeleteMapping("/kullanicilar/{id}")
	public void deleteKullanici(@PathVariable int id) {
		kullaniciRepository.deleteKullanici(id);
	}
	

}
