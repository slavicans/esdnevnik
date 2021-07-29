package com.iktpreobuka.esdnevnik.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.config.AuthenticationEntryPoint;
import com.iktpreobuka.esdnevnik.controllers.util.RESTError;
import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Ocena;
import com.iktpreobuka.esdnevnik.entities.Odeljenje;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnik;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnikOdeljenje;
import com.iktpreobuka.esdnevnik.entities.Ucenik;
import com.iktpreobuka.esdnevnik.repositories.NastavnikRepo;
import com.iktpreobuka.esdnevnik.repositories.OcenaRepo;
import com.iktpreobuka.esdnevnik.repositories.OdeljenjeRepo;
import com.iktpreobuka.esdnevnik.repositories.PredmetNastavnikOdeljenjeRepo;
import com.iktpreobuka.esdnevnik.repositories.PredmetNastavnikRepo;
import com.iktpreobuka.esdnevnik.repositories.PredmetRepo;
import com.iktpreobuka.esdnevnik.repositories.UcenikRepo;
import com.iktpreobuka.esdnevnik.security.Views;

@Service
public class OcenaServiceImpl implements OcenaService {
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;
	@Autowired
	private NastavnikRepo nastavnikRepo;
	@Autowired
	private UcenikRepo ucenikRepo;
	@Autowired
	private OdeljenjeRepo odeljenjeRepo;
	@Autowired
	private PredmetRepo predmetRepo;
	@Autowired
	private PredmetNastavnikRepo predmetNastavnikRepo;
	@Autowired
	private PredmetNastavnikOdeljenjeRepo predmetNastavnikOdeljenjeRepo;
	@Autowired
	private OcenaRepo ocenaRepo;

	@Override
	public ResponseEntity<?> oceniUcenika(Ocena newOcena, Integer id, String imePredmeta, String logovaniKorisnik) {
		try {
			Nastavnik logNastavnik = nastavnikRepo.findByEmail(logovaniKorisnik);
			Ucenik ucenik = ucenikRepo.findById(id).get();
			Odeljenje odelj = ucenik.getOdeljenje();
			Predmet predmet = predmetRepo.findByNazivPredmetaAndRazred(imePredmeta, odelj.getRazred());

			if (logNastavnik != null) {
				PredmetNastavnik pN = predmetNastavnikRepo.findByNastavnikAndPredmet(logNastavnik, predmet);
				if(pN!=null){
					PredmetNastavnikOdeljenje pNO = predmetNastavnikOdeljenjeRepo.findByPredmetNastavnikAndOdeljenje(pN,
						odelj);
					if(pNO!=null) {
						Ocena ocena = new Ocena();
						ocena.setBrojcanaOcena(newOcena.getBrojcanaOcena());
						ocena.setPno(pNO);
						ocena.setUcenik(ucenik);
						ocena.setDatumOcenjivanja(java.time.LocalDate.now());
						ocenaRepo.save(ocena);
						return new ResponseEntity<Ocena>(ocena, HttpStatus.CREATED);
					}
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1,
					"Moguce greske: Nastavnik koji se ulogovao ne predaje uceniku./Nastavnik koji se ulogovao ne predaje uneseni predmet./Ucenik ne postoju u bazi."),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { 
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	


	@Override
	public ResponseEntity<?> pregledOcena(Integer ucenik_id) {
		Ucenik ucenik = ucenikRepo.findById(ucenik_id).get();	
		List<Ocena> oceneUcenika=ocenaRepo.findAllByUcenik(ucenik);	
		return new ResponseEntity<List<Ocena>> (oceneUcenika,HttpStatus.OK);
	}



	@Override
	public ResponseEntity<?> pregledOcenaRoditelj(Integer ucenik_id, Korisnik logKor) {
		try {
			Ucenik ucenik = ucenikRepo.findById(ucenik_id).get();
			
			if (ucenik!=null) {
				if(ucenik.getRoditelj().equals(logKor)) {
					List<Ocena> oceneUcenika=ocenaRepo.findAllByUcenik(ucenik);	
					return new ResponseEntity<List<Ocena>> (oceneUcenika,HttpStatus.OK);
				}
			}
			return new ResponseEntity<RESTError>(new RESTError(1,
					"Roditelj ne moze da vidi ocene drugih ucenika."),
					HttpStatus.ALREADY_REPORTED);
		} catch (Exception e) { 
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}


}
