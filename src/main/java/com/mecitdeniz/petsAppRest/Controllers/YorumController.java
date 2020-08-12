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

import com.mecitdeniz.petsAppRest.Entities.Basvuru;
import com.mecitdeniz.petsAppRest.Entities.Ilan;
import com.mecitdeniz.petsAppRest.Entities.Mesaj;
import com.mecitdeniz.petsAppRest.Entities.Sikayet;
import com.mecitdeniz.petsAppRest.Entities.Yorum;
import com.mecitdeniz.petsAppRest.Repositories.BasvuruRepository;
import com.mecitdeniz.petsAppRest.Repositories.IlanRepository;
import com.mecitdeniz.petsAppRest.Repositories.MesajRepository;
import com.mecitdeniz.petsAppRest.Repositories.SikayetRepository;
import com.mecitdeniz.petsAppRest.Repositories.YorumRepository;

@RestController
public class YorumController {

	
	@Autowired
	private YorumRepository yorumRepository;
	
	@Autowired
	private IlanRepository ilanRepository;
	
	@Autowired
	private SikayetRepository sikayetRepository;
	
	@Autowired
	private BasvuruRepository basvuruRepository;
	
	@Autowired
	private MesajRepository mesajRepository;
	
	
	@GetMapping("/yorumlar/{kullaniciId}/ortalama")
	private Float kullaniciOyOrtalamasiniGetir(@PathVariable("kullaniciId") int kullaniciId) {
		
		Float ortalama = 0.f;
		
		List<Yorum> yorumlar = yorumRepository.kullaniciIdIleYorumlariGetir(kullaniciId);
		for(int i = 0; i<yorumlar.size();i++) {
			ortalama = ortalama + yorumlar.get(i).getOy();
		}
		return ortalama/yorumlar.size();
	}
	
	@GetMapping("/yorumlar/{kullaniciId}")
	private List<Yorum> kullaniciIdIleYorumlariGetir(@PathVariable("kullaniciId") int kullaniciId){
		return yorumRepository.kullaniciIdIleYorumlariGetir(kullaniciId);
		
	}
	
	@PostMapping("/yorumlar")
	private ResponseEntity<Integer> yourmEkle(@RequestBody Yorum yorum){
		long t = System.currentTimeMillis();
		Date tarih = new Date(t);
		
		try {
			yorum.setYorumlamaTarihi(tarih.toString());
			yorumRepository.save(yorum);
			Ilan i = ilanRepository.ilanGetirIdIle(yorum.getIlanId());
			List<Sikayet> sikayetler = sikayetRepository.ilanIdIleSikayetGetir(i.getId());
			List<Basvuru> basvurular = basvuruRepository.findAll();
			for(int index = 0; index < sikayetler.size();index++) {
				sikayetRepository.deleteById(sikayetler.get(index).getId());
			}
			for(int index = 0; index < basvurular.size();index++) {
				if(basvurular.get(index).getIlanId() == i.getId()) {
					List<Mesaj> mesajlar = mesajRepository.basvuruIdIleMesajlariGetir(basvurular.get(index).getId());
					for(int j = 0; j<mesajlar.size();j++) {
						mesajRepository.deleteById(mesajlar.get(j).getId());
					}
					basvuruRepository.deleteById(basvurular.get(index).getId());
				}
			}
			ilanRepository.deleteById(i.getId());
			return new ResponseEntity<Integer>(1,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
}
