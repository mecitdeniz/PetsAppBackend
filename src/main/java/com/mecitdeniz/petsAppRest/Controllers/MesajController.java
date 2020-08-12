package com.mecitdeniz.petsAppRest.Controllers;

import java.sql.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Mesaj;
import com.mecitdeniz.petsAppRest.Repositories.MesajRepository;

@RestController
public class MesajController {

	@Autowired
	private MesajRepository mesajRepository;
	
	@GetMapping("/mesajlar/{basvuruId}")
	private List<Mesaj> basvuruIdIleMesajlariGetir(@PathVariable("basvuruId") int basvuruId){
		
		return mesajRepository.basvuruIdIleMesajlariGetir(basvuruId);
	}
	
	@PostMapping("/mesajlar")
	private ResponseEntity<Integer> mesajOlustur(@RequestBody Mesaj mesaj){
		
		long t = System.currentTimeMillis();
		Date tarih = new Date(t); 
	
		Mesaj m = new Mesaj();
		m.setAlanId(mesaj.getAlanId());
		m.setBasvuruId(mesaj.getBasvuruId());
		m.setGonderenId(mesaj.getGonderenId());
		m.setMesaj(mesaj.getMesaj());
		m.setGonderilmeTarihi(tarih);
		mesajRepository.save(m);
		return new ResponseEntity<Integer>(0,HttpStatus.OK);
		
	}
	
	
	
	
	
}


