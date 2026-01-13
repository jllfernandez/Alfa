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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles", schema = "matrix", catalog = "")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	@Setter
	public Long id;

	@Column(name = "code", length = 15)
	@Getter
	@Setter
	public String code;

	@Column(name = "descr", length = 35)
	@Getter
	@Setter
	public String desc;

	 @ManyToMany(fetch = FetchType.EAGER)
	//@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CAPABILITYANDROLE", schema = Constants.SCHEMA, joinColumns = @JoinColumn(name = "ID_ROL"), inverseJoinColumns = @JoinColumn(name = "ID_CAPABILITY"))
	@Getter
	@Setter
	public List<Capability> capabilities;

}
