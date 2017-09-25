package com.mattcx.t4bn.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sitenurse")
public class Sitenurse {

	public Sitenurse() { }
	public Sitenurse(Long id) { this.sitenurseId = id; }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sitenurseId;
	@NotNull
	private Long siteId;
	@NotNull
	private Long nurseId;
	private Timestamp crtDatetime;

	@Transient 
	private String nurseNo;
	@Transient 
	private String nurseName;
	 
	 
//	@ManyToOne
//	private Site site;
//	@ManyToOne
//	private Nurse nurse;	
	
	public Long getSitenurseId() { return sitenurseId; }
	public void setSitenurseId(Long value) { this.sitenurseId = value; }
	
	public Long getSiteId() { return siteId; }
	public void setSiteId(Long value) { this.siteId = value; }
	  
	public Long getNurseId() { return nurseId; }
	public void setNurseId(Long value) { this.nurseId = value; }
	
	public Timestamp getCrtDatetime() { return crtDatetime; }
	public void setCrtDatetime(Timestamp value) { this.crtDatetime = value; }	
	
	public String getNurseNo() { return nurseNo; }
	public void setNurseNo(String nurseNo) { this.nurseNo = nurseNo; }
	
	public String getNurseName() { return nurseName; }
	public void setNurseName(String nurseName) { this.nurseName = nurseName; }	
	
//	public Site getSite() { return site; }
//	public void setSite(Site site) { this.site = site; }
//
//	public Nurse getNurse() { return nurse; }
//	public void setNurse(Nurse nurse) { this.nurse = nurse; }

}