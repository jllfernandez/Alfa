package com.luke.bie.entitys;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.luke.bie.utils.Constants;
/*
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
*/

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CAPABILITYES", schema = Constants.SCHEMA, catalog = "")
public class Capabilityes implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 4, scale = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	public Long id;

	@Column(name = "CODE", length = 15)
	@Getter
	@Setter
	public String code;

	@Column(name = "DESCR", length = 35)
	@Getter
	@Setter
	public String descr;

	@ManyToMany(fetch = FetchType.EAGER)
	//@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CAPABILITYANDROLE", schema = Constants.SCHEMA, joinColumns = @JoinColumn(name = "ID_CAPABILITY"), inverseJoinColumns = @JoinColumn(name = "ID_ROLE"))
	@Getter
	@Setter
	public List<Roles> roles;

}
