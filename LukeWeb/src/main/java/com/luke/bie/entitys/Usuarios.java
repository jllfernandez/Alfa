package com.luke.bie.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.luke.bie.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USUARIOS", schema = Constants.SCHEMA, catalog = "")
public class Usuarios implements java.io.Serializable {

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

	@Column(name = "LOGIN", length = 15)
	@Getter
	@Setter
	public String login;

	@Column(name = "PASS", length = 15)
	@Getter
	@Setter
	public String pass;

	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	@Getter
	@Setter
	public Roles roles;
}
