package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.PredmetNastavnik;

public interface PredmetNastavnikRepo extends CrudRepository<PredmetNastavnik, Integer> {



	PredmetNastavnik findByNastavnikAndPredmet(Nastavnik logNastavnik, Predmet predmet);




}
