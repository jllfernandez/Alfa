package com.luke.master.entitys;

//import org.springframework.data.annotation.Immutable;

import com.luke.master.utils.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Immutable
@Table(name = "USUARIOS", schema = Constants.SCHEMA, catalog = "")
public class Usuarios implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
	@SequenceGenerator(sequenceName = "SEQ_USUARIOS", allocationSize = 1, name = "USUARIO_SEQ")
	@Getter
	@Setter
	public Long id;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ROLE", nullable = false)
	public Roles roles;

	@Column(name = "LOGIN", length = 15)
	@Getter
	@Setter
	public String login;

	@Column(name = "PASS", length = 15)
	@Getter
	@Setter
	public String pass;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "ID_ROLE", nullable = false) public Roles getRoles() {
	 * return this.roles; }
	 * 
	 * public void setRoles(Roles roles) { this.roles = roles; }
	 * 
	 */

}
