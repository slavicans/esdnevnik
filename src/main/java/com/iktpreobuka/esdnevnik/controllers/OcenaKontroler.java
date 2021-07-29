package com.iktpreobuka.esdnevnik.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.controllers.util.RESTError;
import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Ocena;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.Ucenik;
import com.iktpreobuka.esdnevnik.repositories.KorisnikRepo;
import com.iktpreobuka.esdnevnik.repositories.NastavnikRepo;
import com.iktpreobuka.esdnevnik.security.Views;
import com.iktpreobuka.esdnevnik.services.KorisnikService;
import com.iktpreobuka.esdnevnik.services.OcenaService;

@RestController
public class OcenaKontroler {
	@Autowired
	private OcenaService ocenaService;
	@Autowired
	private KorisnikService korisnikService;
	@Autowired
	private KorisnikRepo korisnikRepo;

	@Secured("ROLE_NASTAVNIK")
	@RequestMapping(method = RequestMethod.POST, path = "/oceniUcenika/{id}/predmet/{imePredmeta}")
	public ResponseEntity<?> oceniUcenika(@RequestBody Ocena newOcena, @PathVariable Integer id,
			@PathVariable String imePredmeta) {
		String logovaniKorisnik = korisnikService.getLoggedUser();
		return ocenaService.oceniUcenika(newOcena, id, imePredmeta, logovaniKorisnik);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/pregledSvihOcenaUcenika/{ucenik_id}")
	public ResponseEntity<?> pregledOcena(@PathVariable Integer ucenik_id) {
		String logovaniKorisnik = korisnikService.getLoggedUser();
		Korisnik logKor = korisnikRepo.findByEmail(logovaniKorisnik);
		if (logKor.getRole().getId().equals(4))
			try {
				if (logKor.getKorisnik_id().equals(ucenik_id)) {
					return ocenaService.pregledOcena(ucenik_id);
				}
				return new ResponseEntity<RESTError>(new RESTError(1, "Ucenik moze videti samo svoje ocene."),
						HttpStatus.ALREADY_REPORTED);
			} catch (Exception e) {
				return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
		if (logKor.getRole().getId().equals(3))
			return ocenaService.pregledOcenaRoditelj(ucenik_id,logKor);
		
		return ocenaService.pregledOcena(ucenik_id);
	}

}
