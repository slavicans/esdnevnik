package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Korisnik;

public interface KorisnikRepo extends CrudRepository<Korisnik, Integer> {

	Korisnik findByEmail(String email);


	Korisnik findByImeAndPrezimeAndRoleIdAndEmail(String ime, String prezime, Integer roleId, String email);

}
