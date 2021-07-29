package com.iktpreobuka.esdnevnik.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;



@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PredmetNastavnik {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer predNast_id;
//
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "korisnik_id")
//	@JsonManagedReference
//	@JsonView(Views.Private.class)
	protected Nastavnik nastavnik;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "predmet_id")
	@JsonView(Views.Private.class)
//	@JsonManagedReference
	protected Predmet predmet;

	public PredmetNastavnik() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PredmetNastavnik(Integer predNast_id, Nastavnik nastavnik, Predmet predmet) {
		super();
		this.predNast_id = predNast_id;
		this.nastavnik = nastavnik;
		this.predmet = predmet;
	}

	public Integer getPredNast_id() {
		return predNast_id;
	}

	public void setPredNast_id(Integer predNast_id) {
		this.predNast_id = predNast_id;
	}

	public Nastavnik getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}
	
	
	
//	@ManyToOne
//	@JoinColumn(name = "odeljenje_id")
//	@JsonManagedReference
//	protected Predmet odeljenje;
	
//	 @OneToMany(mappedBy = "predmetNastavnik")
//	 @JsonBackReference
//	private List<PredmetNastavnikOdeljenje> raspodelaPNO;
	
	

}
