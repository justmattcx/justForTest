package com.mattcx.t4bn.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.transaction.annotation.Transactional;

import com.mattcx.t4bn.model.Site;

@Transactional
public interface SiteDao extends CrudRepository<Site, Long> {
 
}
