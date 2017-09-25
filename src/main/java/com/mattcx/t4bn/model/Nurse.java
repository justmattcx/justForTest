package com.mattcx.t4bn.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nurse")
public class Nurse {

	public Nurse() { }

	public Nurse(Long id) { this.nurseId = id; }
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nurseId;
	@NotNull
	private String nurseNo;
	@NotNull
	private String nurseName;
	private Timestamp crtDatetime;	  
	private Timestamp updDatetime;
	   
//    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "nurse")
//	private Set<Sitenurse> Sitenurses;			

	public Long getNurseId() { return nurseId; }
	public void setNurseId(Long value) { this.nurseId = value; }

	public String getNurseNo() { return nurseNo; }
	public void setNurseNo(String value) { this.nurseNo = value; }
	  
	public String getNurseName() { return nurseName; }
	public void setNurseName(String value) { this.nurseName = value; }
	
	public Timestamp getCrtDatetime() { return crtDatetime; }
	public void setCrtDatetime(Timestamp value) { this.crtDatetime = value; }
	  
	public Timestamp getUpdDatetime() { return updDatetime; }
	public void setUpdDatetime(Timestamp value) { this.updDatetime = value; }  
	
//	public Set<Sitenurse> getSitenurses() { return Sitenurses; }
//	public void setSitenurses(Set<Sitenurse> sitenurses) { Sitenurses = sitenurses; }		
	
	@Override
	public String toString() {
		return "Nurse [nurseId=" + nurseId + ", nurseNo=" + nurseNo + ", nurseName=" + nurseName + ", crtDatetime="
				+ crtDatetime + ", updDatetime=" + updDatetime + "]";
	}    
	
}