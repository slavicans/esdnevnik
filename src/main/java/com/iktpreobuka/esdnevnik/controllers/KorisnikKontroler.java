package com.iktpreobuka.esdnevnik.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.controllers.dto.UserTokenDTO;
import com.iktpreobuka.esdnevnik.entities.Korisnik;
import com.iktpreobuka.esdnevnik.entities.Nastavnik;
import com.iktpreobuka.esdnevnik.entities.Roditelj;
import com.iktpreobuka.esdnevnik.entities.RoleEntity;
import com.iktpreobuka.esdnevnik.entities.Ucenik;
import com.iktpreobuka.esdnevnik.repositories.KorisnikRepo;
import com.iktpreobuka.esdnevnik.repositories.NastavnikRepo;
import com.iktpreobuka.esdnevnik.repositories.RoditeljRepo;
import com.iktpreobuka.esdnevnik.repositories.RoleRepo;
import com.iktpreobuka.esdnevnik.repositories.UcenikRepo;
import com.iktpreobuka.esdnevnik.security.Views;
import com.iktpreobuka.esdnevnik.services.KorisnikService;
import com.iktpreobuka.esdnevnik.services.PredmetService;
import com.iktpreobuka.esdnevnik.utils.Encryption;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class KorisnikKontroler {
	public Korisnik ulogovani = new Korisnik();
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikRepo korisnikRepo;

	@Autowired
	private RoditeljRepo roditeljRepo;
	@Autowired
	private NastavnikRepo nastavnikRepo;
	@Autowired
	private UcenikRepo ucenikRepo;
	@Autowired
	private RoleRepo roleRepo;

	

	@Value("${spring.security.secret-key}")
	private String secretKey;

	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(path = "/korisnici", method = RequestMethod.GET)
	@JsonView(Views.Admin.class)
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(korisnikRepo.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

		Korisnik user = korisnikRepo.findByEmail(email);
		ulogovani = user;
		
		if (user != null && Encryption.validatePasswprd(password, user.getPassword())) {

			String token = getJWTToken(user);
			UserTokenDTO userDTO = new UserTokenDTO(user.getEmail(), token);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}

		return new ResponseEntity<>("Password/email dont match", HttpStatus.UNAUTHORIZED);
	}

	private String getJWTToken(Korisnik user) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRole().getName());
		String token = Jwts.builder().setId("softtekJWT").setSubject(user.getEmail())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 6000000))
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();

		return "Bearer " + token;
	}

//	@Secured ("ROLE_ADMIN")
//	@RequestMapping(method = RequestMethod.POST, path = "/dodajAdmina")
//	public ResponseEntity<?> createUser(@RequestBody Korisnik newUser) {
//		Integer roleId=1;
//			Korisnik user = new Korisnik();
//			user.setIme(newUser.getIme());
//			user.setEmail(newUser.getEmail());
//			user.setPrezime(newUser.getPrezime());
//			user.setPassword(newUser.getPassword());
//			RoleEntity role1 = roleRepo.findById(roleId).get();
//			user.setRole(role1);
//			korisnikRepo.save(user);
//			return new ResponseEntity<Korisnik>(user, HttpStatus.CREATED);
//	}
	
	@Secured ("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, path = "/dodajAdmina")
	public ResponseEntity<?> createAdmin(@RequestBody Korisnik newUser) {
		return korisnikService.createAdmin(newUser);
	}
	
	@Secured ({"ROLE_ADMIN","ROLE_NASTAVNIK"})
	@RequestMapping( method = RequestMethod.POST, path = "/dodajUcenika")
	public ResponseEntity<?> createStudent(@RequestBody Ucenik newUser,
			@RequestParam  Integer r,@RequestParam Integer o,
			@RequestParam String irod, @RequestParam String prod,
			@RequestParam String email, @RequestParam String password2){
		return korisnikService.createStudent(newUser,r,o,irod,prod,email,password2);
	}
	
	@Secured ("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, path = "/dodajNastavnika")
	public ResponseEntity<?> createTeacher(@RequestBody Nastavnik newUser) {
		return korisnikService.createTeacher(newUser);
	}
	@Secured ({"ROLE_ADMIN","ROLE_NASTAVNIK"})
	@RequestMapping(method = RequestMethod.POST, path = "/dodajRoditelja")
	public ResponseEntity<?> createParrent(@RequestBody Roditelj newUser) {
		return korisnikService.createParrent(newUser);
	}
	
	@Secured ("ROLE_ADMIN")
		@RequestMapping(method=RequestMethod.DELETE, path = "delete/{id}")
		public List<Korisnik> deleteKorisnik(@PathVariable Integer id) {
			korisnikRepo.deleteById(id);
			return (List<Korisnik>)korisnikRepo.findAll();
		}
	
	@Secured ("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.PUT, path = "/changeUser/{id}")
	public ResponseEntity<?> changeUser(@PathVariable Integer id,@RequestBody Korisnik changeUser) {
		return korisnikService.changeUser(id, changeUser);
	}
	
	
}
