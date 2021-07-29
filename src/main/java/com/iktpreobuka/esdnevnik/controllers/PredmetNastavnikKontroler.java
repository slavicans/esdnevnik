package com.iktpreobuka.esdnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.esdnevnik.services.PredmetNastavnikService;



@RestController
public class PredmetNastavnikKontroler {
	
		@Autowired
		private PredmetNastavnikService predmetNastavnikService;
		
		@Secured ("ROLE_ADMIN")
		@RequestMapping(method = RequestMethod.POST, path = "/podela")
		public ResponseEntity<?> podelaNaIzvrsioce(@RequestParam Integer nastavnikId,@RequestParam String imePredmeta, @RequestParam Integer razred, @RequestParam Integer oznaka ) {
			return predmetNastavnikService.podelaNaIzvrsioce(nastavnikId, imePredmeta,razred,oznaka);
		}

}
