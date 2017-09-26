package com.mattcx.t4bn.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
public class SiteService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SiteDao siteDao;
    @Autowired
    NurseDao nurseDao; 
    @Autowired
    SitenurseDao sitenurseDao;
    
    /** 
     * 新增站點 
     * --
     * 
     * */   
	public void doAddSite(String  siteName) {
		
    	Site site = new Site();
    	site.setSiteName(siteName);
    	site.setCrtDatetime(new Timestamp(System.currentTimeMillis()));
    	site.setUpdDatetime(new Timestamp(System.currentTimeMillis()));
		siteDao.save(site);
	}

    /** 
     * 查詢站點清單 
     * --
     * 
     * */  
	public List<Site> findAll() {
    	List<Site> siteList = (List<Site>)siteDao.findAll();
    	logger.trace("siteList: {}", siteList);
    	
		return siteList;
	}

    /** 
     * 依據站點id, 取得站點資料
     * --
     * 
     * */ 	
	public Site findOne(Long id) {
    	Site site = siteDao.findOne(id);
    	logger.trace("site="+site.toString()); 
		return site;
	}

    /** 
     * 依據站點id, 取得該站點關連的護士註冊站點資料
     * --
     * 
     * */ 	
	public List<Sitenurse> findSiteNurseListBySiteId(Long id) {

        List<Sitenurse> sitenurseList = sitenurseDao.findBySiteId(id);
        List<Sitenurse> showSitenurseList = new ArrayList<Sitenurse>();
        
        logger.trace("sitenurseList: {}", sitenurseList.toString());
        // List<Nurse> nurseList = new ArrayList<Nurse>();
        for(Sitenurse sitenurse : sitenurseList){        	
        	Nurse n = nurseDao.findOne(sitenurse.getNurseId());
        	sitenurse.setNurseNo(n.getNurseNo());
        	sitenurse.setNurseName(n.getNurseName());
        	showSitenurseList.add(sitenurse);
        }       
        logger.trace("showSitenurseList: {}", showSitenurseList);
		
		return showSitenurseList;
	}

    /** 
     * 依據站點id, 修改站點資訊
     * --
     * 
     * */ 		
	public void editSite(Long id, String siteName) {
    	Site site = siteDao.findOne(id);
    	site.setSiteName(siteName);
    	site.setUpdDatetime(new Timestamp(System.currentTimeMillis()));    	
    	siteDao.save(site);
	}

    /** 
     * 依據站點id, 刪除站點資訊及護士註冊站點資訊
     * --
     * 
     * */ 	
	public void deleteSiteInfo(long id) {		
    	List<Sitenurse> doDelSitenurses = sitenurseDao.findBySiteId(id);
    	sitenurseDao.delete(doDelSitenurses);    	
    	Site site = siteDao.findOne(id);
    	siteDao.delete(site);    
	}  	
	
	
	
	
	
	
	
}
