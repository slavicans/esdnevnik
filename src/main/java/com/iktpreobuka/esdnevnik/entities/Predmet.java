package com.iktpreobuka.esdnevnik.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Predmet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer predmet_id;

	@JsonView(Views.Private.class)
	@NotNull (message = "Podatak o nazivu predmeta mora da se popuni.")
	@Column(nullable = false)
	private String nazivPredmeta;

	@JsonView(Views.Private.class)
	@NotNull(message = "Podatak o fondu casova mora da se popuni.")
	@Column(nullable = false)
	private Integer razred;

	@JsonView(Views.Private.class)
	@Column(nullable = false)
	private Integer fondCasova;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Predmet_Odeljenje", joinColumns = {
			@JoinColumn(name = "predmet_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "odeljenje_id", nullable = false, updatable = false) })
//	@NotBlank(message = "Odeljenje mora da se popuni.")
//	@JsonView(Views.Private.class)
	protected Set<Odeljenje> odeljenja = new HashSet<Odeljenje>();

//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinTable(name = "Predmet_Nastavnik", joinColumns =
//			{@JoinColumn(name = "Predmet_id", nullable = false, updatable = false) },
//			inverseJoinColumns = { @JoinColumn(name = "Korisnik_id", nullable = false, updatable = false) })
//	@NotBlank(message = "Odeljenje mora da se popuni.")
//	@JsonView(Views.Private.class)
//	protected Set<Nastavnik> nastavnici = new HashSet<Nastavnik>();

//veza sa predmetNastavnik//
	@JsonIgnore
	@OneToMany(mappedBy = "predmet")
//	 @JsonBackReference
	List<PredmetNastavnik> raspodela;

	@Version
	private Integer version;

	public Predmet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Predmet(Integer predmet_id, String nazivPredmeta, Integer razred, Integer fondCasova,
			Set<Odeljenje> odeljenja, List<PredmetNastavnik> raspodela, Integer version) {
		super();
		this.predmet_id = predmet_id;
		this.nazivPredmeta = nazivPredmeta;
		this.razred = razred;
		this.fondCasova = fondCasova;
		this.odeljenja = odeljenja;
		this.raspodela = raspodela;
		this.version = version;
	}

	public Integer getPredmet_id() {
		return predmet_id;
	}

	public void setPredmet_id(Integer predmet_id) {
		this.predmet_id = predmet_id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public Integer getRazred() {
		return razred;
	}

	public void setRazred(Integer razred) {
		this.razred = razred;
	}

	public Integer getFondCasova() {
		return fondCasova;
	}

	public void setFondCasova(Integer fondCasova) {
		this.fondCasova = fondCasova;
	}

	public Set<Odeljenje> getOdeljenja() {
		return odeljenja;
	}

	public void setOdeljenja(Set<Odeljenje> odeljenja) {
		this.odeljenja = odeljenja;
	}

	public List<PredmetNastavnik> getRaspodela() {
		return raspodela;
	}

	public void setRaspodela(List<PredmetNastavnik> raspodela) {
		this.raspodela = raspodela;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
