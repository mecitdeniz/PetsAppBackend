package com.mecitdeniz.petsAppRest.Controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Basvuru;
import com.mecitdeniz.petsAppRest.Entities.Hayvan;
import com.mecitdeniz.petsAppRest.Entities.Ilan;
import com.mecitdeniz.petsAppRest.Entities.Kullanici;
import com.mecitdeniz.petsAppRest.Entities.Mesaj;
import com.mecitdeniz.petsAppRest.Entities.Sikayet;
import com.mecitdeniz.petsAppRest.Repositories.BasvuruRepository;
import com.mecitdeniz.petsAppRest.Repositories.HayvanRepository;
import com.mecitdeniz.petsAppRest.Repositories.IlanRepository;
import com.mecitdeniz.petsAppRest.Repositories.KullaniciRepository;
import com.mecitdeniz.petsAppRest.Repositories.MesajRepository;
import com.mecitdeniz.petsAppRest.Repositories.SikayetRepository;

@RestController
public class BasvuruController {

	
	@Autowired
	private BasvuruRepository basvuruRepository;
	
	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	@Autowired
	private MesajRepository mesajRepository;
	
	@Autowired
	private HayvanRepository hayvanRepository;
	
	@Autowired
	private IlanRepository ilanRepository;
	
	@Autowired
	private SikayetRepository sikayetRepository;
	
	@GetMapping("/ilanlar/{ilanId}/basvurular")
	private List<Basvuru> ilanIdIleBasvurulariGetir(@PathVariable int ilanId){
		
		return basvuruRepository.ilanIdIleBasvurulariGetir(ilanId);
	}
	
	@GetMapping("/ilanlar/{ilanId}/basvuru")
	private Basvuru ilanIdIleBasvuruGetir(@PathVariable int ilanId){
		
		Optional<Basvuru> basvuru = basvuruRepository.ilanIdIleBasvuruGetir(ilanId);
		if(basvuru.isPresent()) {
			return basvuru.get();
		}else {
			return null;
		}
	}
	
	@GetMapping("/basvurular/{basvuruId}/onayla/{kullaniciId}")
	private ResponseEntity<Integer> basvuruOnayla(@PathVariable("basvuruId") int basvuruId,
			@PathVariable("kullaniciId") int kullaniciId){
		
		Optional<Kullanici> k = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
		
		Optional<Basvuru> basvuru = basvuruRepository.findById(basvuruId);
		
		if(k.isPresent() && basvuru.isPresent() && 
				basvuru.get().getIlan().getYayinlayanId() == k.get().getId()) {
			Ilan ilan = basvuru.get().getIlan();
			
			List<Basvuru> basvurular = basvuruRepository.ilanIdIleBasvurulariGetir(ilan.getId());
			try {
				if(ilan.getTipId() == 1) {
					List<Sikayet> sikayetler = sikayetRepository.ilanIdIleSikayetGetir(ilan.getId());
					Hayvan h = ilan.getHayvanlar().get(0);
					h.setSahipId(basvuru.get().getBasvuranId());
					hayvanRepository.save(h);
					for(int index = 0; index < sikayetler.size();index++) {
						sikayetRepository.deleteById(sikayetler.get(index).getId());
					}
					for(int index = 0; index < basvurular.size();index++) {
						List<Mesaj> mesajlar = mesajRepository.basvuruIdIleMesajlariGetir(basvurular.get(index).getId());
						for(int i = 0; i<mesajlar.size();i++) {
							mesajRepository.deleteById(mesajlar.get(i).getId());
						}
						basvuruRepository.deleteById(basvurular.get(index).getId());
					}
					ilanRepository.deleteById(ilan.getId());
					
				}else {
					ilan.setDurumId(5);
					ilanRepository.save(ilan);
					
					for(int i = 0; i<basvurular.size();i++) {
						Basvuru b = basvurular.get(i);
						if(b.getId() != basvuru.get().getId()) {
							b.setDurumId(3);
							basvuruRepository.save(b);
						}else {
							b.setDurumId(2);
							basvuruRepository.save(b);
						}
					}
				}
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}
	
	@GetMapping("/basvurular/{basvuruId}/reddet/{kullaniciId}")
	private ResponseEntity<Integer> basvuruReddet(@PathVariable("basvuruId") int basvuruId,
			@PathVariable("kullaniciId") int kullaniciId){
		
		Optional<Kullanici> k = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
		
		Optional<Basvuru> basvuru = basvuruRepository.findById(basvuruId);
		
		if(k.isPresent() && basvuru.isPresent() && 
				basvuru.get().getIlan().getYayinlayanId() == k.get().getId()) {
			
			try {
				basvuru.get().setDurumId(3);
				basvuruRepository.save(basvuru.get());
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}	
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		
	}
	
	@GetMapping("/kullanicilar/{kullaniciId}/basvurular")
	private List<Basvuru> kullaniciIdIleBasvurulariGetir(@PathVariable int kullaniciId){
		
		return basvuruRepository.kullaniciIdIleIlanGetir(kullaniciId);
	}
	
	@DeleteMapping("/kullanicilar/{kullaniciId}/basvurular/{basvuruId}")
	private ResponseEntity<Integer> basvuruSil(@PathVariable("kullaniciId") int kullaniciId,
			@PathVariable("basvuruId") int basvuruId){
		
		Optional<Kullanici> k = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
	
		List<Mesaj> mesajlar = mesajRepository.basvuruIdIleMesajlariGetir(basvuruId);
		
		Optional<Basvuru> b = basvuruRepository.findById(basvuruId);
		if(k.isPresent() && b.isPresent() && k.get().getId() == b.get().getBasvuranId()) {
			try {
				for(int index = 0; index < mesajlar.size(); index++) {
					mesajRepository.deleteById(mesajlar.get(index).getId());
				}
				basvuruRepository.deleteById(basvuruId);
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.METHOD_NOT_ALLOWED);
		}
		
		
	}
	
	@PostMapping("/basvurular")
	private ResponseEntity<Integer> basvuruOlustur(@RequestBody Basvuru basvuru) {
		
		List<Basvuru> basvurular = basvuruRepository.ilanIdIleBasvurulariGetir(basvuru.getIlanId());
		
		long t = System.currentTimeMillis();
		Date tarih = new Date(t); 
		
		boolean basvurmusMu = false;
		for(int i=0;i<basvurular.size();i++) {
			if(basvurular.get(i).getBasvuranId() == basvuru.getBasvuranId()) {
				basvurmusMu = true;
			}
		}
		if(!basvurmusMu) {
			basvuru.setDurumId(1);
			basvuru.setBasvuruTarihi(tarih);
			basvuruRepository.save(basvuru);
			return new ResponseEntity<Integer>(0,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.CONFLICT);
		}
				
				
	}
	
	
	
	
	
	
	
	
	
}
