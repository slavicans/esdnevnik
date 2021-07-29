package com.iktpreobuka.esdnevnik.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;


@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@PrimaryKeyJoinColumn(name = "korisnik_id")

public class Nastavnik extends Korisnik {

//	@OneToMany(mappedBy = "predmet", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<Predmet> predmeti;

//	@OneToMany(mappedBy = "nastavnik", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JsonIgnore
//	@JsonView(Views.Private.class)
//	@JsonBackReference
//	private List<Odeljenje> odeljenja;

//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinTable(name = "Predmet_Nastavnik", joinColumns = {
//			@JoinColumn(name = "Korisnik_id", nullable = false, updatable = false) }, inverseJoinColumns = {
//					@JoinColumn(name = "Predmet_id", nullable = false, updatable = false) })
//	@NotBlank(message = "Odeljenje mora da se popuni.")
//	@JsonView(Views.Private.class)
//	protected Set<Predmet> predmeti = new HashSet<Predmet>();

	//veza sa predmetNastavnik
	@JsonIgnore
	@OneToMany(mappedBy = "nastavnik")
//	@JsonBackReference
//	@JsonView(Views.Private.class)
	List <PredmetNastavnik> raspodela;

	public Nastavnik() {
		super();
		// TODO Auto-generated constructor stub
	}
















	public Nastavnik(Integer korisnik_id, @NotBlank(message = "Ime ucenika mora da se popuni.") String ime,
			@NotBlank(message = "Prezime korisnika mora da se popuni.") String prezime, RoleEntity role,
			@NotBlank(message = " Email ora biti unesen!") @Email(message = "Email nije vazeci.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email  nije vazeci.") String email,
			@NotBlank(message = " Lozinka mora biti unesena! ") @Size(min = 8, max = 500, message = "Duzina lozinke mora biti izmedju {min} i {max}.") String password,
			Integer version) {
		super(korisnik_id, ime, prezime, role, email, password, version);
		// TODO Auto-generated constructor stub
	}
















	public Nastavnik(List<PredmetNastavnik> raspodela) {
		super();
		this.raspodela = raspodela;
	}

	public List<PredmetNastavnik> getRaspodela() {
		return raspodela;
	}

	public void setRaspodela(List<PredmetNastavnik> raspodela) {
		this.raspodela = raspodela;
	}

	

	
}
