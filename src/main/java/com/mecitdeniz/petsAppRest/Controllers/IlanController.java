package com.mecitdeniz.petsAppRest.Controllers;

import java.sql.Date;

import java.util.Collections;
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

import com.mecitdeniz.petsAppRest.Entities.Basvuru;
import com.mecitdeniz.petsAppRest.Entities.Ilan;
import com.mecitdeniz.petsAppRest.Entities.Kullanici;
import com.mecitdeniz.petsAppRest.Entities.Mesaj;
import com.mecitdeniz.petsAppRest.Entities.Sikayet;
import com.mecitdeniz.petsAppRest.Repositories.BasvuruRepository;
import com.mecitdeniz.petsAppRest.Repositories.IlanRepository;
import com.mecitdeniz.petsAppRest.Repositories.KullaniciRepository;
import com.mecitdeniz.petsAppRest.Repositories.MesajRepository;
import com.mecitdeniz.petsAppRest.Repositories.SikayetRepository;

@RestController
public class IlanController {

	
	@Autowired
	private IlanRepository ilanRepository;
	
	@Autowired
	private KullaniciRepository kullaniciRepository;
	
	@Autowired
	private SikayetRepository sikayetRepository;
	
	@Autowired
	private BasvuruRepository basvuruRepository;
	
	@Autowired
	private MesajRepository mesajRepository;
	
	@GetMapping("/ilanlar")
	public List<Ilan> onaylananIlanlariGetir(){
		List<Ilan> ilanlar = ilanRepository.ilanlariGetir(2); 
		Collections.reverse(ilanlar);
		return ilanlar;
	}
	
	@GetMapping("/ilanlar/bekleyen")
	public List<Ilan> bekleyenIlanlariGetir(){
		return ilanRepository.ilanlariGetir(1); 
		
	}
	
	@GetMapping("/{kullaniciId}/ilanlar")
	private List<Ilan> kullaniciIlanlariniGetir(@PathVariable int kullaniciId){
		return ilanRepository.kullaniciIdIleGetir(kullaniciId);
	}

	@GetMapping("/ilanlar/{id}")
	private Ilan ilanGetirIdIle(@PathVariable int id) {
		return ilanRepository.ilanGetirIdIle(id);
	}
	
	@GetMapping("/ilanlar/{ilanId}/onayla/{editorId}")
	private ResponseEntity<Integer> ilanOnayla(@PathVariable("ilanId") int ilanId,
			@PathVariable("editorId") int editorId){

		Ilan i = ilanRepository.ilanGetirIdIle(ilanId);
		Optional<Kullanici> e = kullaniciRepository.idIleKullaniciGetir(editorId);
		
		if(e.isPresent() && e.get().getRolId() == 2) {
			i.setDurumId(2);
			ilanRepository.save(i);
			return new ResponseEntity<Integer>(1,HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	@GetMapping("/ilanlar/{ilanId}/reddet/{editorId}")
	private ResponseEntity<Integer> ilanReddet(@PathVariable("ilanId") int ilanId,
			@PathVariable("editorId") int editorId){

		Ilan i = ilanRepository.ilanGetirIdIle(ilanId);
		Optional<Kullanici> e = kullaniciRepository.idIleKullaniciGetir(editorId);
		
		if(e.isPresent() && e.get().getRolId() == 2) {
			i.setDurumId(3);
			ilanRepository.save(i);
			return new ResponseEntity<Integer>(1,HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	@PutMapping("/kullanicilar/{kullaniciId}/ilanlar/{ilanId}")
	private ResponseEntity<Integer> ilanDuzenle(@RequestBody Ilan ilan,@PathVariable("kullaniciId") int kullaniciId,
			@PathVariable("ilanId") int ilanId){
		
		Optional<Kullanici> k = kullaniciRepository.idIleKullaniciGetir(kullaniciId);
		
		Ilan i = ilanRepository.ilanGetirIdIle(ilanId);
		if(i != null && ilan != null && k.isPresent() 
				&& i.getYayinlayanId() == k.get().getId()) {
			try {
				i.setBaslik(ilan.getBaslik());
				i.setAciklama(ilan.getAciklama());
				i.setIlId(ilan.getIlId());
				i.setIlceId(ilan.getIlceId());
				i.setDurumId(1);
				if(i.getTipId() == 2) {
					i.setBaslangicTarihi(ilan.getBaslangicTarihi());
					i.setBitisTarihi(ilan.getBitisTarihi());
				}
				ilanRepository.save(i);
				return new ResponseEntity<Integer>(1,HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.METHOD_NOT_ALLOWED);
		}
		
	}
	
	@GetMapping("/ilanlar/{ilanId}/yayindanKaldir/{editorId}")
	private ResponseEntity<Integer> ilanYayindanKaldir(@PathVariable("ilanId") int ilanId,
			@PathVariable("editorId") int editorId){

		Ilan i = ilanRepository.ilanGetirIdIle(ilanId);
		Optional<Kullanici> e = kullaniciRepository.idIleKullaniciGetir(editorId);
		
		if(e.isPresent() && e.get().getRolId() == 2) {
			i.setDurumId(4);
			ilanRepository.save(i);
			return new ResponseEntity<Integer>(1,HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.BAD_GATEWAY);
		}
		
	}
	
	@DeleteMapping("/{kullaniciId}/ilanlar/{ilanId}")
	public ResponseEntity<Integer> idIleIlanSil(@PathVariable("kullaniciId") int kullaniciId,@PathVariable("ilanId") int ilanId){
		
		List<Sikayet> sikayetler = sikayetRepository.ilanIdIleSikayetGetir(ilanId);
		List<Basvuru> basvurular = basvuruRepository.findAll();
		for(int index = 0; index < sikayetler.size();index++) {
			sikayetRepository.deleteById(sikayetler.get(index).getId());
		}
		for(int index = 0; index < basvurular.size();index++) {
			if(basvurular.get(index).getIlanId() == ilanId) {
				List<Mesaj> mesajlar = mesajRepository.basvuruIdIleMesajlariGetir(basvurular.get(index).getId());
				for(int i = 0; i<mesajlar.size();i++) {
					mesajRepository.deleteById(mesajlar.get(i).getId());
				}
				basvuruRepository.deleteById(basvurular.get(index).getId());
			}
		}
		Ilan i = ilanRepository.ilanGetirIdIle(ilanId);
		if(i.getYayinlayan().getId() == kullaniciId) {
			
			ilanRepository.deleteById(ilanId);
			return new ResponseEntity<Integer>(0,HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>(0,HttpStatus.METHOD_NOT_ALLOWED);	
		}
		
	}
	
	
	@PostMapping("/ilanlar")
	private ResponseEntity<Integer> ilanEkle(@RequestBody Ilan ilan) {
		
		
		long t = System.currentTimeMillis();
		Date tarih = new Date(t); 
		
		Ilan i = new Ilan();
		
		i.setBaslik(ilan.getBaslik());
		i.setAciklama(ilan.getAciklama());
		i.setDurumId(1);
		i.setTipId(ilan.getTipId());
		i.setYayinlayanId(ilan.getYayinlayanId());
		i.setHayvanlar(ilan.getHayvanlar());
		i.setYayinTarihi(tarih.toString());
		i.setIlceId(ilan.getIlceId());
		i.setIlId(ilan.getIlId());
		if(ilan.getTipId() == 2) {
			i.setBaslangicTarihi(ilan.getBaslangicTarihi());
			i.setBitisTarihi(ilan.getBitisTarihi());
		}
		System.out.println(ilan);
		System.out.println("---------------------------------");
		System.out.println(i);
		ilanRepository.save(i);
		
		return new ResponseEntity<Integer>(0,HttpStatus.CREATED);
	}
	
	
}
