package com.mattcx.t4bn.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.mattcx.t4bn.model.Nurse;


@Transactional
public interface NurseDao extends CrudRepository<Nurse, Long> {



	
}
