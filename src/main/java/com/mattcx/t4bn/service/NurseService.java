package com.mattcx.t4bn.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mattcx.t4bn.dao.NurseDao;
import com.mattcx.t4bn.dao.SiteDao;
import com.mattcx.t4bn.dao.SitenurseDao;
import com.mattcx.t4bn.model.Nurse;
import com.mattcx.t4bn.model.Site;
import com.mattcx.t4bn.model.Sitenurse;

@Service
public class NurseService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SiteDao siteDao;
    @Autowired
    NurseDao nurseDao; 
    @Autowired
    SitenurseDao sitenurseDao;
	
	/** 
     * 查詢可供註冊的站點清單
     * --
     *  
     * */
	public List<Site> findAllSite() {
	     List<Site> siteList = (List<Site>)siteDao.findAll();
	     logger.trace("siteList: {}",siteList);
	     return siteList;
	}

	/** 
     * 新增護士資料, 並同時註冊站點
     * --
     *  
     * */
	public void doAddNurse(String nurseNo, String nurseName, String[] to) {
		
    	Nurse newNurse = new Nurse();
    	newNurse.setNurseNo(nurseNo);
    	newNurse.setNurseName(nurseName);
    	newNurse.setCrtDatetime(new Timestamp(System.currentTimeMillis()));    
    	newNurse.setUpdDatetime(new Timestamp(System.currentTimeMillis()));     		
		
    	newNurse = nurseDao.save(newNurse);
    	Long nurseId = newNurse.getNurseId();
		logger.trace("NurseId="+nurseId);
		
    	Set<Sitenurse> sitenurseSet = new HashSet<Sitenurse>();        	
    	if(null!=to && to.length>0) {
    		for(int i=0; i<to.length; i++){
    			logger.trace(("to["+i+"]=")+to[i]);
    			Long siteId = Long.parseLong(to[i], 10);
    			Sitenurse sitenurse = new Sitenurse();
    			sitenurse.setSiteId(siteId);
    			sitenurse.setNurseId(nurseId);
    			sitenurse.setCrtDatetime(new Timestamp(System.currentTimeMillis()));
    			sitenurseSet.add(sitenurse);
    		}
    	}  	
    	 if(null!=sitenurseSet && !sitenurseSet.isEmpty()) {        	
    		 sitenurseDao.save(sitenurseSet);
    	 }		
	}
	
	/** 
     * 查詢所有護士資料
     * --
     *  
     * */
	public List<Nurse> findAllNurse() {
		
		List<Nurse> nurseList = (List<Nurse>)nurseDao.findAll();
		logger.trace("nurseList: {}",nurseList);
		return nurseList;
	}

	/** 
     * 查詢單筆護士資料, by nurseId
     * --
     *  
     * */
	public Nurse findNurseByNurseId(Long nurseId) {
		
		Nurse nurse = nurseDao.findOne(nurseId);
		logger.trace("nurse="+nurse.toString());  
		return nurse;
	}
	
	/** 
     * 編輯護士資料頁, 取得 已分配站點清單/可再分配站點清單, by nurseId
     * --
     *  return Map<String, List<Sitenurse>>
     *  
     *  1. map.put("sitenurseList", 已分配站點List);
     *  2. map.put("sitenurseList", 可再分配站點清單List);
     *  
     * */	
	public Map<String, List<Site>> findSitenurseMap_editPage(Long nurseId) {
	
        // 取得 已分配站點清單/可再分配站點清單
        Map<String, List<Site>> sitenurseMap = new HashMap<String, List<Site>>();		
		
        // 站點護士中間表...
        List<Sitenurse> sitenurseList = sitenurseDao.findByNurseId(nurseId);
        logger.trace("sitenurseList: {}", sitenurseList);
		
        // 已分配站點...
        List<Site> nurseSiteList = new ArrayList<Site>();
        for(Sitenurse sitenurse : sitenurseList){        	
        	nurseSiteList.add(siteDao.findOne(sitenurse.getSiteId()));
        }     		
        sitenurseMap.put("nurseSiteList", nurseSiteList);
        logger.trace("已分配站點>>>nurseSiteList: {}", nurseSiteList);        
        
        // 可分配站點
        List<Site> siteList = (List<Site>)siteDao.findAll();
        if( null!=siteList && !siteList.isEmpty() 
        	&& null!=nurseSiteList && !nurseSiteList.isEmpty()) 
        {
        	siteList.removeAll(nurseSiteList);
        }
        sitenurseMap.put("siteList", siteList);
        logger.trace("可分配站點>>>siteList: {}", siteList);        
        
		return sitenurseMap;
	}
	
	/** 
     * 儲存護士主擋(修改)+儲存護士註冊站點檔(新增+刪除)
     * --
     *  
     * */
	public void saveNurseInfo(Long nurseId, String nurseNo, String nurseName, String[] to) {
				
    	// 儲存護士主擋
    	Nurse updNurse = nurseDao.findOne(nurseId);
    	updNurse.setNurseNo(nurseNo);
    	updNurse.setNurseName(nurseName);
    	updNurse.setUpdDatetime(new Timestamp(System.currentTimeMillis()));    	
    	//nurse.setSites(siteSet);
    	nurseDao.save(updNurse);
    	
    	/* 儲存站點護士註冊中間表 */
    	// 1. 取得該名護士 現有註冊表
        List<Sitenurse> sitenurseList = sitenurseDao.findByNurseId(updNurse.getNurseId());
        // logger.trace("sitenurseList: {}", sitenurseList);  
        List<String> nowSiteIds = new ArrayList<String>();
        sitenurseList.forEach(i -> nowSiteIds.add(i.getSiteId().toString()));
        logger.trace("1. 取得該名護士 現有註冊表=>nowSiteIds: {}", nowSiteIds);
                
    	// 2. 取得該名護士目前選取的註冊表站點id
        List<String> newSiteIds = new ArrayList<String>();
        if(null!=to && to.length>0) {
        	newSiteIds = Arrays.asList(to);
        }
        logger.trace("2. 取得該名護士目前選取的註冊表站點id=>newSiteIds: {}", newSiteIds);
                
        // 3. 新增 取得該名護士 需要 新增註冊站點id
        List<String> doAddList = new ArrayList<String>();
        Set<Sitenurse> doAddSet = new HashSet<Sitenurse>();
        newSiteIds.forEach(i->{
            if (!nowSiteIds.contains(i)) { 
            	doAddList.add(i); 
            	Sitenurse newsn= new Sitenurse();
            	newsn.setSiteId(new Long(i));
            	newsn.setNurseId(nurseId);
            	newsn.setCrtDatetime(new Timestamp(System.currentTimeMillis()));
            	doAddSet.add(newsn);
            } 
        });
        if(null!=doAddSet && !doAddSet.isEmpty()) {
        	sitenurseDao.save(doAddSet);
        } 
        logger.trace("3. 新增 取得該名護士 需要 新增註冊站點id=>doAddSiteIds: {}", doAddList);
        
        // 4. 刪除 取得該名護士 需要刪除註冊站點id
        List<String> doDelList = new ArrayList<String>();
        Set<Sitenurse> doDelSet = new HashSet<Sitenurse>();
        
        for(String id: nowSiteIds) {
            if ( !newSiteIds.contains(id)) { 
            	doDelList.add(id); 
            	Sitenurse delsn = sitenurseDao.findBySiteIdNurseId(new Long(id), nurseId);
            	doDelSet.add(delsn);
            }       	
        }        
//        nowSiteIds.forEach(i->{
//            if ( !newSiteIds.contains(i)) { 
//            	doDelList.add(i); 
//            	Sitenurse delsn = sitenurseDao.findBySiteIdNurseId(new Long(i), nurseId);
//            	doDelSet.add(delsn);
//            	logger.trace("2-7");
//            }
//        });       
                
        if(null!=doDelSet && !doDelSet.isEmpty()) {
        	sitenurseDao.delete(doDelSet);
        }
        
        logger.trace("4. 刪除 取得該名護士 需要刪除註冊站點id=>doDelList: {}", doDelList);		
	}

    /** 
     * 依據護士id, 刪除護士資訊及護士註冊站點資訊
     * --
     * 
     * */ 	
	public void deleteSiteInfo(long nurseId) {
    	List<Sitenurse> doDelSitenurses = sitenurseDao.findByNurseId(nurseId);
    	sitenurseDao.delete(doDelSitenurses);
    	
    	Nurse nurse = nurseDao.findOne(nurseId);
    	nurseDao.delete(nurse);
	}    
		
}