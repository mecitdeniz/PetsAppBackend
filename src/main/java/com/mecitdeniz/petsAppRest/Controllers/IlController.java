package com.mecitdeniz.petsAppRest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Il;
import com.mecitdeniz.petsAppRest.Repositories.IlRepository;

@RestController
public class IlController {

	
	@Autowired
	private IlRepository ilRepository;
	
	@GetMapping("/iller")
	private List<Il> illeriGetir(){
			
		return ilRepository.findAll(); 
	}
	
	@GetMapping("/iller/{id}")
	private List<Il> idIleIlleriGetir(@PathVariable("id") int ilId){
			
		return ilRepository.idIleIlleriGetir(ilId); 
	}
}
