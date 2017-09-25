package com.mattcx.t4bn.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "site")
public class Site {	

	public Site(){}
	public Site(Long id) { this.siteId = id; }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long siteId;  
	@NotNull
	private String siteName;
	private Timestamp crtDatetime;  
	private Timestamp updDatetime;

//	@OneToMany(mappedBy="site")
//	private Set<Sitenurse> Sitenurses;			
	
	public long getSiteId() { return siteId; }
	public void setSiteId(long siteId) { this.siteId = siteId; }

	public String getSiteName() { return siteName; }
	public void setSiteName(String siteName) { this.siteName = siteName; }

	public Timestamp getCrtDatetime() { return crtDatetime; }
	public void setCrtDatetime(Timestamp crtDatetime) { this.crtDatetime = crtDatetime; }

	public Timestamp getUpdDatetime() { return updDatetime; }
	public void setUpdDatetime(Timestamp updDatetime) { this.updDatetime = updDatetime; }
	
//	public Set<Sitenurse> getSitenurses() { return Sitenurses; }
//	public void setSitenurses(Set<Sitenurse> sitenurses) { Sitenurses = sitenurses; }		
	
    @Override
	public String toString() {
		return "Site [siteId=" + siteId + ", siteName=" + siteName + ", crtDatetime=" + crtDatetime + ", updDatetime="
				+ updDatetime + "]";
	}    
  
}