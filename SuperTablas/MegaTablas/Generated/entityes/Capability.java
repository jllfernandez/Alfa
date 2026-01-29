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
@Table(name = "PROC", schema = "CEPAL", catalog = "")
public class Capability implements java.io.Serializable {



    private static final long serialVersionUID = 1L;

@Id
@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
 @GeneratedValue(strategy=GenerationType.IDENTITY)
@Getter
@Setter
public Long id;

@Column(name = "ID_CEPAL", length = 50)
@Getter
@Setter
public String cepal;

@Column(name = "VER_CEPAL", length = 5)
@Getter
@Setter
public String vercepal;

@Column(name = "FEC_INC", length = )
@Getter
@Setter
public Date fec;

@Column(name = "DES", length = 100)
@Getter
@Setter
public String desc;

@Column(name = "BPM2_XML", length = )
@Getter
@Setter
public String bpm;



}
