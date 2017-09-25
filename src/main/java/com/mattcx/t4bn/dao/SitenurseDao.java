package com.mattcx.t4bn.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mattcx.t4bn.model.Sitenurse;

@Transactional
public interface SitenurseDao extends CrudRepository<Sitenurse, Long> {
	
	
//	   @Query("SELECT uf.departureGps, uf.flight.id, uf.flight.flightNumber, uf.flight.airline.name, uf.flight.departureDate, " +
//	            "uf.flight.departureAirport.name FROM UserFlight uf WHERE user.id=?1")
//	    List<Sitenurse> getSitenursesBySiteId(Long siteId);	
	
//	@Query("select sn from sitenurse sn join sn.site join sn.nurse where sn.siteId=?1")
//	List<Sitenurse> getSitenurseBysiteId(Long siteId);		
	
//	@Query(value = "select * from book b where b.siteId=?1", nativeQuery = true)
//	public List<Sitenurse> findBySiteId(Long siteId);	
	
	@Query("select sn from Sitenurse sn where sn.siteId=?1")
	public List<Sitenurse> findBySiteId(Long siteId);	
	
	@Query("select sn from Sitenurse sn where sn.nurseId=?1")
	public List<Sitenurse> findByNurseId(Long nurseId);		
	
	@Query("select sn from Sitenurse sn where sn.siteId=?1 and sn.nurseId=?2")
	public Sitenurse findBySiteIdNurseId(Long siteId, Long nurseId);		
	
}
