package com.iktpreobuka.esdnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.esdnevnik.controllers.util.RESTError;
import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.repositories.PredmetRepo;
import com.iktpreobuka.esdnevnik.services.KorisnikService;

import com.iktpreobuka.esdnevnik.services.PredmetService;

@RestController
public class PredmetKontroler {
	@Autowired
	private PredmetService predmetService;
	@Autowired
	private PredmetRepo predmetRepo;
	
	
	@Secured ("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, path = "/dodajPredmet")
	public ResponseEntity<?> createSubject(@RequestBody Predmet newSubject) {
		return predmetService.createSubject(newSubject);
	}
	
	
	@Secured ("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.DELETE, path = "deletePredmet/{nazivPredmeta}/{razred}")
	public List<Predmet> deletePredmet(@PathVariable Integer razred,@PathVariable String nazivPredmeta) {
			Predmet pred = predmetRepo.findByNazivPredmetaAndRazred(nazivPredmeta, razred);
			if(pred!=null) 
				predmetRepo.deleteById(pred.getPredmet_id());
			return (List<Predmet>)predmetRepo.findAll();
}
	
	
//	public ResponseEntity<List<Predmet>>  deletePredmet(@PathVariable Integer razred,@PathVariable String nazivPredmeta){
//		Predmet pred = predmetRepo.findByNazivPredmetaAndRazred(nazivPredmeta, razred);
//		if(pred!=null) {
//			predmetRepo.deleteById(pred.getPredmet_id());			
//		}
//		return (ResponseEntity<List<Predmet>>)predmetRepo.findAll();
//	}
}
