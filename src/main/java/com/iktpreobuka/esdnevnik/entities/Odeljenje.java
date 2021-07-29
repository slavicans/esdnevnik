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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;



@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Odeljenje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer odeljenje_id;

	@JsonView(Views.Private.class)
//	@NotNull(message = "Razred mora da se popuni.")
//	@Column(nullable = false)
	@Min(value = 1, message = "Razred mora biti izmedju 1 i 8!")
	@Max(value = 8, message = "Razred mora biti izmedju 1 i 8!")
	protected Integer razred;

	@JsonView(Views.Private.class)
//	@NotBlank(message = "Oznaka odeljenja  mora da se popuni.")
//	@Column(nullable = false)
	protected Integer oznaka;

	@JsonIgnore
	@OneToMany(mappedBy = "odeljenje", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//	@JsonBackReference
	private List<Ucenik> ucenici;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Predmet_Odeljenje", joinColumns = {
			@JoinColumn(name = "odeljenje_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "predmet_id", nullable = false, updatable = false) })
	protected Set<Predmet> predmeti = new HashSet<Predmet>();

	@JsonIgnore
	@OneToMany(mappedBy = "odeljenje")
//	@JsonBackReference
	private List<PredmetNastavnikOdeljenje> raspodelaPNO;

	public Odeljenje() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Odeljenje(Integer odeljenje_id,
			@NotBlank(message = "Razred mora da se popuni.") @Min(value = 1, message = "Razred mora biti izmedju 1 i 8!") @Max(value = 8, message = "Razred mora biti izmedju 1 i 8!") @NotBlank(message = "Razred mora biti izmedju {min} i {max}.") Integer razred,
			@NotBlank(message = "Oznaka odeljenja  mora da se popuni.") Integer oznaka, List<Ucenik> ucenici,
			Set<Predmet> predmeti, List<PredmetNastavnikOdeljenje> raspodelaPNO) {
		super();
		this.odeljenje_id = odeljenje_id;
		this.razred = razred;
		this.oznaka = oznaka;
		this.ucenici = ucenici;
		this.predmeti = predmeti;
		this.raspodelaPNO = raspodelaPNO;
	}

	public Integer getOdeljenje_id() {
		return odeljenje_id;
	}

	public void setOdeljenje_id(Integer odeljenje_id) {
		this.odeljenje_id = odeljenje_id;
	}

	public Integer getRazred() {
		return razred;
	}

	public void setRazred(Integer razred) {
		this.razred = razred;
	}

	public Integer getOznaka() {
		return oznaka;
	}

	public void setOznaka(Integer oznaka) {
		this.oznaka = oznaka;
	}

	public List<Ucenik> getUcenici() {
		return ucenici;
	}

	public void setUcenici(List<Ucenik> ucenici) {
		this.ucenici = ucenici;
	}

	public Set<Predmet> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(Set<Predmet> predmeti) {
		this.predmeti = predmeti;
	}

	public List<PredmetNastavnikOdeljenje> getRaspodelaPNO() {
		return raspodelaPNO;
	}

	public void setRaspodelaPNO(List<PredmetNastavnikOdeljenje> raspodelaPNO) {
		this.raspodelaPNO = raspodelaPNO;
	}


}
