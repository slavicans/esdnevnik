package com.iktpreobuka.esdnevnik.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.esdnevnik.entities.Predmet;

public interface PredmetService {

	ResponseEntity<?> createSubject(Predmet newSubject);

}
