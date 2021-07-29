package com.iktpreobuka.esdnevnik.services;

import org.springframework.http.ResponseEntity;

public interface PredmetNastavnikService {

	ResponseEntity<?> podelaNaIzvrsioce(Integer nastavnikId, String imePredmeta, Integer razred, Integer oznaka);

}
