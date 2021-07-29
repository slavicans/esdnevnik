package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Odeljenje;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnik;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnikOdeljenje;

public interface PredmetNastavnikOdeljenjeRepo extends CrudRepository<PredmetNastavnikOdeljenje, Integer> {



	PredmetNastavnikOdeljenje findByPredmetNastavnikAndOdeljenje(PredmetNastavnik pN, Odeljenje odelj);



}
