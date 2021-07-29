package com.iktpreobuka.esdnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.DiscriminatorType;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;


@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Inheritance(strategy= InheritanceType.JOINED)

public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@JsonView(Views.Public.class)
	protected Integer korisnik_id;
	
//	@JsonView(Views.Private.class)
//	@NotBlank(message = "Ime korisnika mora da se popuni.")
	@Column(nullable = false)
	protected String ime;
	
//	@JsonView(Views.Private.class)
//	@NotBlank(message = "Prezime korisnika mora da se popuni.")
	@Column(nullable = false)
	protected String prezime;
	
//	@JsonView(Views.Admin.class)
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "role")
	protected RoleEntity role;
	
	//@JsonView(Views.Admin.class)
//	@NotBlank(message = " Email ora biti unesen!")
	
	@Email(message = "Email nije vazeci.")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message="Email  nije vazeci.")
	@Column (nullable=false)
	private String email;
	
	
	
//	@NotBlank(message = " Lozinka mora biti unesena! ")
	@Size (min = 8, max = 500, message = "Duzina lozinke mora biti izmedju {min} i {max}.")
//	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}")
//	@Column (nullable=false)
	private String password;
	
	@Version
	private Integer version;

	public Korisnik() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Korisnik(Integer korisnik_id, @NotBlank(message = "Ime ucenika mora da se popuni.") String ime,
			@NotBlank(message = "Prezime korisnika mora da se popuni.") String prezime, RoleEntity role,
			@NotBlank(message = " Email ora biti unesen!") @Email(message = "Email nije vazeci.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email  nije vazeci.") String email,
			@NotBlank(message = " Lozinka mora biti unesena! ") @Size(min = 8, max = 500, message = "Duzina lozinke mora biti izmedju {min} i {max}.") String password,
			Integer version) {
		super();
		this.korisnik_id = korisnik_id;
		this.ime = ime;
		this.prezime = prezime;
		this.role = role;
		this.email = email;
		this.password = password;
		this.version = version;
	}





	public Integer getKorisnik_id() {
		return korisnik_id;
	}

	public void setKorisnik_id(Integer korisnik_id) {
		this.korisnik_id = korisnik_id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	
	
}
