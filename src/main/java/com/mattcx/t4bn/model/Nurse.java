package com.mattcx.t4bn.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "nurse")
public class Nurse {

	public Nurse() { }

	public Nurse(long id) { this.nurseId = id; }
	public Nurse(String nurseNo) { this.nurseNo = nurseNo; }
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nurseId;
	  
	@NotNull
	private String nurseNo;
	  
	@NotNull
	private String nurseName;

	private Timestamp crtDatetime;
	  
	private Timestamp updDatetime;
	   
    //@ManyToMany(cascade = CascadeType.ALL)
	//@ManyToMany(cascade = CascadeType.PERSIST)
	@ManyToMany
    @JoinTable(name = "site_nurse"
    			, joinColumns = {@JoinColumn(name = "nurse_id", referencedColumnName = "nurseid")}
    			, inverseJoinColumns = {@JoinColumn(name = "site_id", referencedColumnName = "siteid")})
    private Set<Site> sites;	
	
	public long getNurseId() { return nurseId; }
	public void setNurseId(long value) { this.nurseId = value; }

	public String getNurseNo() { return nurseNo; }
	public void setNurseNo(String value) { this.nurseNo = value; }
	  
	public String getNurseName() { return nurseName; }
	public void setNurseName(String value) { this.nurseName = value; }
	
	public Timestamp getCrtDatetime() { return crtDatetime; }
	public void setCrtDatetime(Timestamp value) { this.crtDatetime = value; }
	  
	public Timestamp getUpdDatetime() { return updDatetime; }
	public void setUpdDatetime(Timestamp value) { this.updDatetime = value; }  
	
    public Set<Site> getSites() { return sites; }	
    public void setSites(Set<Site> sites) { this.sites = sites; }    
	
	@Override
	public String toString() {
		return "Nurse [nurseId=" + nurseId + ", nurseNo=" + nurseNo + ", nurseName=" + nurseName + ", crtDatetime="
				+ crtDatetime + ", updDatetime=" + updDatetime + "]";
	}    
	
}