package com.iktpreobuka.esdnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Ocena;
import com.iktpreobuka.esdnevnik.entities.Ucenik;

public interface OcenaRepo extends CrudRepository<Ocena, Integer> {

	List<Ocena> findAllByUcenik(Ucenik ucenik);
	
	
}
	
