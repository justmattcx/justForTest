package com.mattcx.t4bn.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "site")
public class Site {	

    public Site(){

    }
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long siteId;
	  
	@NotNull
	private String siteName;
  
	@Null
	private Timestamp crtDatetime;
	  
	@Null
	private Timestamp updDatetime;

	public long getSiteId() { return siteId; }
	public void setSiteId(long siteId) { this.siteId = siteId; }

	public String getSiteName() { return siteName; }
	public void setSiteName(String siteName) { this.siteName = siteName; }

	public Timestamp getCrtDatetime() { return crtDatetime; }
	public void setCrtDatetime(Timestamp crtDatetime) { this.crtDatetime = crtDatetime; }

	public Timestamp getUpdDatetime() { return updDatetime; }
	public void setUpdDatetime(Timestamp updDatetime) { this.updDatetime = updDatetime; }
	
	//@ManyToMany(mappedBy = "nurses")
    @ManyToMany
    @JoinTable(name = "site_nurse"
    			, joinColumns = {@JoinColumn(name = "site_id", referencedColumnName = "siteid")}
    			, inverseJoinColumns = {@JoinColumn(name = "nurse_id", referencedColumnName = "nurseid")})	
	private Set<Nurse> nurses;
	
    
    public Set<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(Set<Nurse> nurses) {
        this.nurses = nurses;
    }	
	
}
