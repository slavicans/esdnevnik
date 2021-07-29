package com.iktpreobuka.esdnevnik.services;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Ocena;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.Ucenik;


public interface OcenaService {

	ResponseEntity<?> oceniUcenika(Ocena newOcena, Integer id, String imePredmeta, String logovaniKorisnik);

	ResponseEntity<?> pregledOcena(Integer ucenik_id);

	ResponseEntity<?> pregledOcenaRoditelj(Integer ucenik_id, Korisnik logKor);

	



}
