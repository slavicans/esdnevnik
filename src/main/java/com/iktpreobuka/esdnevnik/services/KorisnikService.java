package com.iktpreobuka.esdnevnik.services;

import org.springframework.http.ResponseEntity;

import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Roditelj;
import com.iktpreobuka.esdnevnik.entities.Ucenik;

public interface KorisnikService {
	ResponseEntity<?> createAdmin(Korisnik newUser);

	ResponseEntity<?> createStudent(Ucenik newUser, Integer r, Integer o, String irod, String prod, String email,
			String password2);

	ResponseEntity<?> createTeacher(Nastavnik newUser);

	ResponseEntity<?> createParrent(Roditelj newUser);

	ResponseEntity<?> changeUser(Integer id, Korisnik changeUser);
	
	public String getLoggedUser();
	

}
