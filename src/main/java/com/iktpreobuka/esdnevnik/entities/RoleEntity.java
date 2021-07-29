package com.iktpreobuka.esdnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.esdnevnik.security.Views;


@Entity
@Table (name="role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class RoleEntity {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Integer id;
	
	@Column(name ="role_name")
	@JsonView(Views.Admin.class)
	private String name;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY ,cascade = { CascadeType.REFRESH})
	private List<Korisnik> users = new ArrayList<>();
	
	public RoleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoleEntity(Integer id, String name, List<Korisnik> users) {
		super();
		this.id = id;
		this.name = name;
		this.users = users;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Korisnik> getUsers() {
		return users;
	}
	public void setUsers(List<Korisnik> users) {
		this.users = users;
	}

	
}
