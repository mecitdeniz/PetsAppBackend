package com.mecitdeniz.petsAppRest.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Kullanici;
import com.mecitdeniz.petsAppRest.Repositories.KullaniciRepository;

@RestController
public class AdminController {

	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	
	@PutMapping("/admin/{adminId}/kullaniciRolGuncelle/{kullaniciId}")
	private ResponseEntity<Integer> kullaniciRolGuncelle(@PathVariable("adminId") int adminId,
			@PathVariable("kullaniciId") int kullaniciId){
		
		Optional<Kullanici> kullanici = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
		Optional<Kullanici> admin = kullaniciRepository.idIleKullaniciGetir(adminId);
		
		if(kullanici.isPresent() && admin.isPresent()) {
			if(admin.get().getRolId() == 3) {
				if(kullanici.get().getRolId() == 1) {
					kullanici.get().setRolId(2);	
				}else if(kullanici.get().getRolId() == 2) {
					kullanici.get().setRolId(1);	
				}
				kullaniciRepository.kullaniciGuncelle(kullanici.get().getId(), kullanici.get());
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			}else {
				return new ResponseEntity<Integer>(-1,HttpStatus.METHOD_NOT_ALLOWED);
			}
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
		}
		
	}
}
