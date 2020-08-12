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

import com.mecitdeniz.petsAppRest.Entities.Hayvan;
import com.mecitdeniz.petsAppRest.Entities.Ilan;
import com.mecitdeniz.petsAppRest.Entities.Kullanici;
import com.mecitdeniz.petsAppRest.Entities.Sikayet;
import com.mecitdeniz.petsAppRest.Repositories.HayvanRepository;
import com.mecitdeniz.petsAppRest.Repositories.KullaniciRepository;

@RestController
public class HayvanController {

	
	@Autowired
	private HayvanRepository hayvanRepository;
	
	@Autowired
	private KullaniciRepository kullaniciRepository;

	@GetMapping("/hayvanlar")
	private List<Hayvan> getAllHayvan(){
		return hayvanRepository.findAll();
	}
	
	@PutMapping("/{kullaniciId}/hayvanlar/{hayvanId}")
	private ResponseEntity<Integer> hayvanGuncelle(@PathVariable("kullaniciId") int kullaniciId,
			@PathVariable("hayvanId") int hayvanId,@RequestBody Hayvan hayvan){
		
		Hayvan h = hayvanRepository.getHayvan(hayvanId);
		Optional<Kullanici> kullanici = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
		if(kullanici.isPresent() && h.getSahipId() == kullanici.get().getId()) {
			try {
				
				h.setAd(hayvan.getAd());
				h.setCinsId(hayvan.getCinsId());
				h.setTurId(hayvan.getTurId());
				h.setCinsiyetId(hayvan.getCinsiyetId());
				h.setResim1(hayvan.getResim1());
				h.setResim2(hayvan.getResim2());
				h.setResim3(hayvan.getResim3());
				h.setResim4(hayvan.getResim4());
				hayvanRepository.save(h);
				
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}
	
	@PostMapping("/hayvanlar")
	private ResponseEntity<Integer> addHayvan(@RequestBody Hayvan hayvan) {
		
		try {
			Hayvan h = new Hayvan();
			h.setAd(hayvan.getAd());
			h.setSahipId(hayvan.getSahipId());
			h.setResim1(hayvan.getResim1());
			h.setResim2(hayvan.getResim2());
			h.setResim3(hayvan.getResim3());
			h.setResim4(hayvan.getResim4());
			h.setTurId(hayvan.getTurId());
			h.setCinsId(hayvan.getCinsId());
			h.setCinsiyetId(hayvan.getCinsiyetId());
			hayvanRepository.addHayvan(h);
			return new ResponseEntity<Integer>(1,HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/{kullaniciId}/hayvanlar/{hayvanId}")
	private ResponseEntity<Integer> idIleIlanSil(@PathVariable("kullaniciId") int kullaniciId,
			@PathVariable("hayvanId") int hayvanId){
		
		Hayvan h = hayvanRepository.getHayvan(hayvanId);
		if(h.getSahipId()== kullaniciId) {
			hayvanRepository.deleteById(hayvanId);
			return new ResponseEntity<Integer>(0,HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>(0,HttpStatus.METHOD_NOT_ALLOWED);	
		}
		
	}
	
	@GetMapping("/hayvanlar/{id}")
	private Hayvan getHayvan(@PathVariable int id){
		return hayvanRepository.getHayvan(id);
	}
	
	
	@GetMapping("/kullanicilar/{id}/hayvanlar")
	private List<Hayvan> getHayvanlar(@PathVariable int id){
		return hayvanRepository.getHayvanlarByKullaniciId(id);
	}
}
