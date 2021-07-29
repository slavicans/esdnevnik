package com.iktpreobuka.esdnevnik.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.esdnevnik.repositories.NastavnikRepo;
import com.iktpreobuka.esdnevnik.repositories.OdeljenjeRepo;
import com.iktpreobuka.esdnevnik.repositories.PredmetNastavnikOdeljenjeRepo;
import com.iktpreobuka.esdnevnik.repositories.PredmetNastavnikRepo;
import com.iktpreobuka.esdnevnik.repositories.PredmetRepo;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.controllers.util.RESTError;
import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnik;
import com.iktpreobuka.esdnevnik.entities.Odeljenje;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnikOdeljenje;

@Service
public class PredmetNastavnikServiceImpl implements PredmetNastavnikService {
	@Autowired
	private PredmetRepo predmetRepo;
	@Autowired
	private NastavnikRepo nastavnikRepo;
	@Autowired
	private OdeljenjeRepo odeljenjeRepo;
	@Autowired
	private PredmetNastavnikRepo predmetNastavnikRepo;
	@Autowired
	private PredmetNastavnikOdeljenjeRepo predmetNastavnikOdeljenjeRepo;

//	@Autowired
//	private PredmetNastavnikOdeljenjeRepo predmetNastavnikOdeljenjeRepo;

	@Override
	public ResponseEntity<?> podelaNaIzvrsioce(Integer nastavnikId, String imePredmeta, Integer razred,
			Integer oznaka) {
		try {
			Predmet pred = predmetRepo.findByNazivPredmetaAndRazred(imePredmeta, razred);
			Nastavnik nastavnik = nastavnikRepo.findById(nastavnikId).get();

			if (pred != null && nastavnik != null) {
				if (nastavnik.getRole().getId().equals(2)) {
					PredmetNastavnik pN = predmetNastavnikRepo.findByNastavnikAndPredmet(nastavnik, pred);
					if (pN == null) {
						pN = new PredmetNastavnik();
						pN.setNastavnik(nastavnik);
						pN.setPredmet(pred);
						predmetNastavnikRepo.save(pN);
					}
					Odeljenje odelj = odeljenjeRepo.findByRazredAndOznaka(razred, oznaka);
					if (odelj != null) {
						PredmetNastavnikOdeljenje pNO = predmetNastavnikOdeljenjeRepo
								.findByPredmetNastavnikAndOdeljenje(pN, odelj);
						if (pNO == null) {
							pNO = new PredmetNastavnikOdeljenje();
							pNO.setPredmetNastavnik(pN);
							pNO.setOdeljenje(odelj);
							predmetNastavnikOdeljenjeRepo.save(pNO);
						}
					}
					return new ResponseEntity<PredmetNastavnik>(pN, HttpStatus.CREATED);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1,
					" GRESKA!!! 1. Kombinacija predmet-nastavnik ili predmet-nastavnik-odeljenje vec postoji u bazi, 2. u bazi ne postoji nastavnik-odeljenje-predmet"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
