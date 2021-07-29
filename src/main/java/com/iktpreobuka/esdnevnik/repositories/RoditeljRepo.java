package com.iktpreobuka.esdnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.Roditelj;

public interface RoditeljRepo extends CrudRepository<Roditelj, Integer> {

	Roditelj findByImeAndPrezime(String irod, String prod);

	Roditelj findByImeAndPrezimeAndRoleIdAndEmail(String ime, String prezime, Integer roleId, String email);

}
