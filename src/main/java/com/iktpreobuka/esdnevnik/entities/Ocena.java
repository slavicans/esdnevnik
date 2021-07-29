package com.iktpreobuka.esdnevnik.entities;

import java.time.LocalDate;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;


@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Ocena {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	protected Integer ocena_id;

	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena mora biti izmedju 1 i 5!")
	@Max(value = 5, message = "Ocena mora biti izmedju 1 i 5!")
	@NotNull(message = "Ocena mora biti izmedju {min} i {max}.")
	@Column(nullable = false)
	private Integer brojcanaOcena;
	
	
	@JsonView(Views.Private.class)
	private LocalDate datumOcenjivanja;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "pno_id")
	@JsonView(Views.Private.class)
//	@JsonManagedReference
	protected PredmetNastavnikOdeljenje pno;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ucenik")
	protected Ucenik ucenik;
	
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
//	@JoinTable(name = "Ocena_PredmetNastavnikOdeljenje", joinColumns =
//	{@JoinColumn(name = "ocena_id", nullable = false, updatable = false )},
//	inverseJoinColumns = { @JoinColumn(name = "pno_id", nullable = false, updatable = false
//	) })
//	protected Set<PredmetNastavnikOdeljenje> pnos = new HashSet<PredmetNastavnikOdeljenje>();
	
	
//	, nullable = false, updatable = false
	//nullable = false, updatable = false
	@Version
	private Integer version;

	public Ocena() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ocena(Integer ocena_id,
			@Min(value = 1, message = "Ocena mora biti izmedju 1 i 5!") @Max(value = 5, message = "Ocena mora biti izmedju 1 i 5!") @NotBlank(message = "Ocena mora biti izmedju {min} i {max}.") Integer brojcanaOcena,
			LocalDate datumOcenjivanja, PredmetNastavnikOdeljenje pno, Ucenik ucenik, Integer version) {
		super();
		this.ocena_id = ocena_id;
		this.brojcanaOcena = brojcanaOcena;
		this.datumOcenjivanja = datumOcenjivanja;
		this.pno = pno;
		this.ucenik = ucenik;
		this.version = version;
	}

	public Integer getOcena_id() {
		return ocena_id;
	}

	public void setOcena_id(Integer ocena_id) {
		this.ocena_id = ocena_id;
	}

	public Integer getBrojcanaOcena() {
		return brojcanaOcena;
	}

	public void setBrojcanaOcena(Integer brojcanaOcena) {
		this.brojcanaOcena = brojcanaOcena;
	}

	public LocalDate getDatumOcenjivanja() {
		return datumOcenjivanja;
	}

	public void setDatumOcenjivanja(LocalDate datumOcenjivanja) {
		this.datumOcenjivanja = datumOcenjivanja;
	}

	public PredmetNastavnikOdeljenje getPno() {
		return pno;
	}

	public void setPno(PredmetNastavnikOdeljenje pno) {
		this.pno = pno;
	}

	public Ucenik getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik ucenik) {
		this.ucenik = ucenik;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


	
}
