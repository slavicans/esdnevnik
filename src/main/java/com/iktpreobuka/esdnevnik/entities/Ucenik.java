package com.iktpreobuka.esdnevnik.entities;

import java.util.List;


import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@PrimaryKeyJoinColumn(name = "korisnik_id")

public class Ucenik extends Korisnik {

	@JsonIgnore
	@JsonView(Views.Private.class)
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "roditelj")
//	@NotNull(message = "Podaci o roditelju moraju da se popune.")
//	@JsonManagedReference
	protected Roditelj roditelj;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje")
	@JsonView(Views.Private.class)
//	@JsonView(Views.Private.class)
//	@NotBlank(message = "Odeljenje mora da se popuni.")
	protected Odeljenje odeljenje;

	@JsonIgnore
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
//	 @JsonView(Views.Public.class)
	protected List<Ocena> ocenaUcenika;

	public Ucenik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ucenik(Integer korisnik_id, @NotBlank(message = "Ime ucenika mora da se popuni.") String ime,
			@NotBlank(message = "Prezime korisnika mora da se popuni.") String prezime, RoleEntity role,
			@NotBlank(message = " Email ora biti unesen!") @Email(message = "Email nije vazeci.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email  nije vazeci.") String email,
			@NotBlank(message = " Lozinka mora biti unesena! ") @Size(min = 8, max = 500, message = "Duzina lozinke mora biti izmedju {min} i {max}.") String password,
			Integer version) {
		super(korisnik_id, ime, prezime, role, email, password, version);
		// TODO Auto-generated constructor stub
	}

	public Ucenik(Roditelj roditelj, Odeljenje odeljenje, List<Ocena> ocenaUcenika) {
		super();
		this.roditelj = roditelj;
		this.odeljenje = odeljenje;
		this.ocenaUcenika = ocenaUcenika;
	}

	public Roditelj getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(Roditelj roditelj) {
		this.roditelj = roditelj;
	}

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	public List<Ocena> getOcenaUcenika() {
		return ocenaUcenika;
	}

	public void setOcenaUcenika(List<Ocena> ocenaUcenika) {
		this.ocenaUcenika = ocenaUcenika;
	}

}
