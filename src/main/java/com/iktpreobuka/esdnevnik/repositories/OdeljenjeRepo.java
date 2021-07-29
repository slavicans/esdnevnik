package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Odeljenje;

public interface OdeljenjeRepo extends CrudRepository<Odeljenje, Integer> {

	public Odeljenje findByRazredAndOznaka(Integer r, Integer o);

}
