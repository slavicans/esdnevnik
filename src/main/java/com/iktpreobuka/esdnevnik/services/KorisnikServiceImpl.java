package com.iktpreobuka.esdnevnik.services;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.iktpreobuka.esdnevnik.controllers.util.RESTError;
import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Odeljenje;
import com.iktpreobuka.esdnevnik.entities.Predmet;
import com.iktpreobuka.esdnevnik.entities.Roditelj;
import com.iktpreobuka.esdnevnik.entities.RoleEntity;
import com.iktpreobuka.esdnevnik.entities.Ucenik;
import com.iktpreobuka.esdnevnik.repositories.KorisnikRepo;
import com.iktpreobuka.esdnevnik.repositories.NastavnikRepo;
import com.iktpreobuka.esdnevnik.repositories.OdeljenjeRepo;
import com.iktpreobuka.esdnevnik.repositories.RoditeljRepo;
import com.iktpreobuka.esdnevnik.repositories.RoleRepo;
import com.iktpreobuka.esdnevnik.repositories.UcenikRepo;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private KorisnikRepo korisnikRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private NastavnikRepo nastavnikRepo;

	@Autowired
	private UcenikRepo ucenikRepo;
	@Autowired
	private RoditeljRepo roditeljRepo;
	@Autowired
	private OdeljenjeRepo odeljenjeRepo;

	public ResponseEntity<?> createAdmin(Korisnik newUser) {
		Integer roleId = 1;
		try {
			Korisnik user = korisnikRepo.findByImeAndPrezimeAndRoleIdAndEmail(newUser.getIme(), newUser.getPrezime(),
					roleId, newUser.getEmail());
			if (user == null) {
				user = new Korisnik();
				user.setIme(newUser.getIme());
				user.setEmail(newUser.getEmail());
				user.setPrezime(newUser.getPrezime());
				user.setPassword(newUser.getPassword());
				RoleEntity role1 = roleRepo.findById(roleId).get();
				user.setRole(role1);
				korisnikRepo.save(user);
				return new ResponseEntity<Korisnik>(user, HttpStatus.CREATED);
			}
			return new ResponseEntity<RESTError>(
					new RESTError(1, "Administrator istog imena i email adrese postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@Override
	public ResponseEntity<?> createStudent(Ucenik newUser, Integer r, Integer o, String irod, String prod, String email,
			String password2) {
		Integer roleId = 4;
		try {
			Ucenik user = ucenikRepo.findByImeAndPrezimeAndRoleIdAndEmail(newUser.getIme(), newUser.getPrezime(),
					roleId, newUser.getEmail());
			if (user == null) {

				user = new Ucenik();
				user.setIme(newUser.getIme());
				user.setEmail(newUser.getEmail());
				user.setPrezime(newUser.getPrezime());
				user.setPassword(newUser.getPassword());
				RoleEntity role1 = roleRepo.findById(roleId).get();
				user.setRole(role1);
				Odeljenje odelj = odeljenjeRepo.findByRazredAndOznaka(r, o);
				if (odelj == null) {
					odelj = new Odeljenje();
					odelj.setOznaka(o);
					odelj.setRazred(r);
					odeljenjeRepo.save(odelj);
				}
				odelj = odeljenjeRepo.findByRazredAndOznaka(r, o);
				user.setOdeljenje(odelj);

				Roditelj rod = roditeljRepo.findByImeAndPrezime(irod, prod);
				if (rod == null) {
					rod = new Roditelj();
					rod.setIme(irod);
					rod.setPrezime(prod);
					rod.setEmail(email);
					rod.setPassword(password2);
					RoleEntity role2 = roleRepo.findById(3).get();
					rod.setRole(role2);
					roditeljRepo.save(rod);
				}
				user.setRoditelj(rod);

				ucenikRepo.save(user);
				return new ResponseEntity<Ucenik>(user, HttpStatus.CREATED);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Ucenik istog imena i email adrese postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public ResponseEntity<?> createTeacher(Nastavnik newUser) {
		Integer roleId = 2;
		try {
			Nastavnik user = nastavnikRepo.findByImeAndPrezimeAndRoleIdAndEmail(newUser.getIme(), newUser.getPrezime(),
					roleId, newUser.getEmail());
			if (user == null) {
				user = new Nastavnik();
				user.setIme(newUser.getIme());
				user.setEmail(newUser.getEmail());
				user.setPrezime(newUser.getPrezime());
				user.setPassword(newUser.getPassword());
				RoleEntity role1 = roleRepo.findById(roleId).get();
				user.setRole(role1);
				nastavnikRepo.save(user);
				return new ResponseEntity<Nastavnik>(user, HttpStatus.CREATED);
			}

			return new ResponseEntity<RESTError>(
					new RESTError(1, "Nastavnik istog imena i email adrese postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	public ResponseEntity<?> createParrent(@RequestBody Roditelj newUser) {
		Integer roleId = 3;
		try {
			Roditelj user = roditeljRepo.findByImeAndPrezimeAndRoleIdAndEmail(newUser.getIme(), newUser.getPrezime(),
					roleId, newUser.getEmail());
			if (user == null) {
				user = new Roditelj();
				user.setIme(newUser.getIme());
				user.setEmail(newUser.getEmail());
				user.setPrezime(newUser.getPrezime());
				user.setPassword(newUser.getPassword());
				RoleEntity role1 = roleRepo.findById(roleId).get();
				user.setRole(role1);
				roditeljRepo.save(user);
				return new ResponseEntity<Roditelj>(user, HttpStatus.CREATED);

			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Roditelj istog imena i email adrese postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@Override
	public ResponseEntity<?> changeUser(Integer id, @RequestBody Korisnik changeUser) {
		try {
			Korisnik user = korisnikRepo.findById(id).get();
			if (user !=null) {
				if(changeUser.getIme() !=null)
					user.setIme(changeUser.getIme());
				if(changeUser.getPrezime() !=null)
					user.setPrezime(changeUser.getPrezime());
				if(changeUser.getEmail() !=null)
					user.setEmail(changeUser.getEmail());
				if(changeUser.getPassword() !=null)
					user.setPassword(changeUser.getPassword());
				korisnikRepo.save(user);
				return new ResponseEntity<Korisnik>(user, HttpStatus.CREATED);
			}
			return new ResponseEntity<RESTError>(new RESTError(1, "Korisnik ne postoji u bazi"),
					HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) { // u slucaju izuzetka vratiti 500
			return new ResponseEntity<RESTError>(new RESTError(2, "Exception occurred:" + e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	
	public String getLoggedUser() {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	
		String currentUserName =authentication.getName();
		return currentUserName;
	}
}
