package com.iktpreobuka.esdnevnik.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Ucenik;

public interface UcenikRepo extends CrudRepository<Ucenik, Integer> {

	

	Ucenik findByImeAndPrezimeAndRoleIdAndEmail(String ime, String prezime, Integer roleId, String email);

	Ucenik findByRoditelj(Korisnik logKor);







	

}
