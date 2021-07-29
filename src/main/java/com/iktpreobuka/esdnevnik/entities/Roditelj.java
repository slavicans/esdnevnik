package com.iktpreobuka.esdnevnik.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@PrimaryKeyJoinColumn(name = "korisnik_id")

public class Roditelj extends Korisnik {

	@JsonIgnore
	@OneToMany(mappedBy = "roditelj", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	private List<Ucenik> ucenici;

	public Roditelj(List<Ucenik> ucenici) {
		super();
		this.ucenici = ucenici;
	}

	public Roditelj() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Roditelj(Integer korisnik_id, @NotBlank(message = "Ime ucenika mora da se popuni.") String ime,
			@NotBlank(message = "Prezime korisnika mora da se popuni.") String prezime, RoleEntity role,
			@NotBlank(message = " Email ora biti unesen!") @Email(message = "Email nije vazeci.") @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email  nije vazeci.") String email,
			@NotBlank(message = " Lozinka mora biti unesena! ") @Size(min = 8, max = 500, message = "Duzina lozinke mora biti izmedju {min} i {max}.") @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}") String password,
			Integer version) {
		super(korisnik_id, ime, prezime, role, email, password, version);
		// TODO Auto-generated constructor stub
	}

	public List<Ucenik> getUcenici() {
		return ucenici;
	}

	public void setUcenici(List<Ucenik> ucenici) {
		this.ucenici = ucenici;
	}

}
