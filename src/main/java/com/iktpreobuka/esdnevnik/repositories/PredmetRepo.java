package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnik;

public interface PredmetRepo extends CrudRepository<Predmet, Integer> {

	Predmet findByNazivPredmetaAndRazred(String nazivPredmeta, Integer razred);







}
