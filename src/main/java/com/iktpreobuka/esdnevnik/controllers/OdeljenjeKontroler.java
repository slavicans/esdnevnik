package com.iktpreobuka.esdnevnik.controllers;

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
import com.iktpreobuka.esdnevnik.entities.Odeljenje;
import com.iktpreobuka.esdnevnik.repositories.OdeljenjeRepo;

@RestController
public class OdeljenjeKontroler {
	@Autowired
	private OdeljenjeRepo odeljenjeRepo;

	@Secured({ "ROLE_ADMIN", "ROLE_NASTAVNIK" })
	@RequestMapping(method = RequestMethod.POST, path = "/dodajOdelj/{r}/{o}")
//	public ResponseEntity<?> createUser(@RequestBody Odeljenje newOdelj) {
	public ResponseEntity<?> createOdelj(@PathVariable Integer r, @PathVariable Integer o) {
		try {
			Odeljenje odelj = odeljenjeRepo.findByRazredAndOznaka(r, o);
			if (odelj == null) {
				odelj = new Odeljenje();
				odelj.setOznaka(o);
				odelj.setRazred(r);
				odeljenjeRepo.save(odelj);
				return new ResponseEntity<Odeljenje>(odelj, HttpStatus.CREATED);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Odeljenje vec postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
}
