package com.iktpreobuka.esdnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.esdnevnik.controllers.util.RESTError;
import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.RoleEntity;
import com.iktpreobuka.esdnevnik.repositories.PredmetRepo;
import com.iktpreobuka.esdnevnik.repositories.RoditeljRepo;
@Service
public class PredmetServiseImpl implements PredmetService {
	@Autowired
	private PredmetRepo predmetRepo;
	@Override
	public ResponseEntity<?> createSubject(Predmet newSubject) {
		try {
			Predmet pred = predmetRepo.findByNazivPredmetaAndRazred(newSubject.getNazivPredmeta(), newSubject.getRazred());
			if (pred == null) {
				pred = new Predmet();
				pred.setNazivPredmeta(newSubject.getNazivPredmeta());
				pred.setRazred(newSubject.getRazred());
				pred.setFondCasova(newSubject.getFondCasova());
				
				predmetRepo.save(pred);
				return new ResponseEntity<Predmet>(pred, HttpStatus.CREATED);
			}
			return new ResponseEntity<RESTError>(
					new RESTError(1, "Predmet za unet razred vec postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	

}
