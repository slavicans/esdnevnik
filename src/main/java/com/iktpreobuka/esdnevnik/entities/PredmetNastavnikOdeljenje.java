package com.iktpreobuka.esdnevnik.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class PredmetNastavnikOdeljenje {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer pno_id;

	///
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "predNast_id")
	@JsonView(Views.Private.class)
	protected PredmetNastavnik predmetNastavnik;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "odeljenje_id")
	@JsonView(Views.Private.class)
	protected Odeljenje odeljenje;
	
///	
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinTable(name = "Ocena_PredmetNastavnikOdeljenje", joinColumns =
//	{@JoinColumn(name = "pno_id", nullable = false, updatable = false) },
//	inverseJoinColumns = { @JoinColumn(name = "ocena_id", nullable = false, updatable = false
//	) })
//	protected Set<Ocena> ocene = new HashSet<Ocena>();

	
	

	public PredmetNastavnikOdeljenje() {
		super();
		// TODO Auto-generated constructor stub
	}

public PredmetNastavnikOdeljenje(Integer pno_id, PredmetNastavnik predmetNastavnik, Odeljenje odeljenje) {
	super();
	this.pno_id = pno_id;
	this.predmetNastavnik = predmetNastavnik;
	this.odeljenje = odeljenje;
}

public Integer getPno_id() {
	return pno_id;
}

public void setPno_id(Integer pno_id) {
	this.pno_id = pno_id;
}

public PredmetNastavnik getPredmetNastavnik() {
	return predmetNastavnik;
}

public void setPredmetNastavnik(PredmetNastavnik predmetNastavnik) {
	this.predmetNastavnik = predmetNastavnik;
}

public Odeljenje getOdeljenje() {
	return odeljenje;
}

public void setOdeljenje(Odeljenje odeljenje) {
	this.odeljenje = odeljenje;
}




}
