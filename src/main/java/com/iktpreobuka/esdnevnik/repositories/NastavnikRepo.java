package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Roditelj;

public interface NastavnikRepo extends CrudRepository<Nastavnik, Integer> {

	void save(Roditelj user);

	Nastavnik findByImeAndPrezimeAndRoleIdAndEmail(String ime, String prezime, Integer roleId, String email);

	

	Nastavnik findByEmail(String logovaniKorisnik);

}
