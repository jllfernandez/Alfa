package com.luke.bie.entitys;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios", schema = "matrix", catalog = "")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	public Long id;

	//@ManyToOne(fetch = FetchType.LAZY)
	 @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", nullable = false)
	@Getter
	@Setter
	public Role role;

	@Column(name = "login", length = 15)
	@Getter
	@Setter
	public String login;

	@Column(name = "pass", length = 15)
	@Getter
	@Setter
	public String pass;

	@Column(name = "fecha")
	@Getter
	@Setter
	public Timestamp date;
}
