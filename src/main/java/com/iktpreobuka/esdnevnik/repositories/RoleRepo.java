package com.iktpreobuka.esdnevnik.repositories;

import javax.management.relation.Role;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.esdnevnik.entities.RoleEntity;

public interface RoleRepo extends CrudRepository<RoleEntity, Integer> {

}
