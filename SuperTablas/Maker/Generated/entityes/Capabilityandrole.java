package com.luke.master.entitys;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "capabilityandrole", schema = "master", catalog = "")
public class Capabilityandrole implements java.io.Serializable {



    private static final long serialVersionUID = 1L;

@ManyToMany(fetch = FetchType.EAGER
@JoinTable(name = "capabilityandrole", schema = "master", joinColumns = @JoinColumn(name = "id_rol"), inverseJoinColumns = @JoinColumn(name = "id_capability"))
@Getter
@Setter
public List<id_capability> id_capabilitys;

@ManyToMany(fetch = FetchType.EAGER
@JoinTable(name = "capabilityandrole", schema = "master", joinColumns = @JoinColumn(name = "id_capability"), inverseJoinColumns = @JoinColumn(name = "id_rol"))
@Getter
@Setter
public List<id_rol> id_rols;



}
