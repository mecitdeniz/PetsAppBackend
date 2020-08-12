package com.mecitdeniz.petsAppRest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mecitdeniz.petsAppRest.Entities.Tur;
import com.mecitdeniz.petsAppRest.Repositories.TurRepository;

@RestController
public class TurController {

	
	@Autowired
	private TurRepository turRepository;
	
	@GetMapping("/turler")
	private List<Tur> turleriGetir(){
		return turRepository.findAll();
	}
}
