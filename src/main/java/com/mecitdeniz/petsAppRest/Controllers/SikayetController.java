package com.mecitdeniz.petsAppRest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Sikayet;
import com.mecitdeniz.petsAppRest.Repositories.SikayetRepository;

@RestController
public class SikayetController {

	
	@Autowired
	private SikayetRepository sikayetRepository;
	
	
	@GetMapping("/sikayetler")
	private List<Sikayet> sikayetGetir(){
		List<Sikayet>sikayetler = sikayetRepository.findAll();
		for(int i = 0;i<sikayetler.size();i++) {
			if(sikayetler.get(i).getIlan().getDurumId() != 2) {
				sikayetler.remove(i);
			}
		}
		return sikayetler;
	}
	
	@GetMapping("/ilanlar/{ilanId}/sikayetler")
	private List<Sikayet> ilanIdIleSikayetGetir(@PathVariable("ilanId") int ilanId){
		return sikayetRepository.ilanIdIleSikayetGetir(ilanId);
	}

	@PostMapping("/sikayetler")
	private ResponseEntity<Integer> sikayeOlustur(@RequestBody Sikayet sikayet){
		
		
		boolean sikayetEtmisMi = false;
		List<Sikayet> sikayetler = sikayetRepository.ilanIdIleSikayetGetir(sikayet.getIlanId());
		
		for(int i=0;i<sikayetler.size();i++) {
			
			if(sikayetler.get(i).getSikayetEdenId() == sikayet.getSikayetEdenId()) {
				sikayetEtmisMi = true;	
			}
		}
		if(!sikayetEtmisMi) {
			sikayetRepository.save(sikayet);
			return new ResponseEntity<Integer>(0,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Integer>(-1,HttpStatus.CONFLICT);
		}
		
	}

	
	
	
	
}
