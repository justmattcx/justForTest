package com.mattcx.t4bn.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mattcx.t4bn.dao.NurseDao;
import com.mattcx.t4bn.dao.SiteDao;
import com.mattcx.t4bn.dao.SitenurseDao;
import com.mattcx.t4bn.model.Nurse;
import com.mattcx.t4bn.model.Site;
import com.mattcx.t4bn.model.Sitenurse;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private NurseDao nurseDao;	
    @Autowired
    private SiteDao siteDao;	    
    @Autowired
    private SitenurseDao sitenurseDao;    
    
    /** 
     * 頁面-新增站點頁
     * --
     *  
     * */
    @RequestMapping("/add")
    public ModelAndView nueseAddPage(){
    	logger.debug("nueseAddPage");
    	
        ModelAndView modelAndView = new ModelAndView("/nurse_new");
        
        List<Site> siteList = new ArrayList<Site>();
    	try {
    		siteList = (List<Site>)siteDao.findAll();    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}        
        modelAndView.addObject("siteList", siteList);        
        logger.trace("siteList: {}",siteList);
        
        return modelAndView;
    }		
	
    /** 
     * 執行-新增-護士
     * --
     * 
     * */
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest req, HttpServletResponse resp) {
    	logger.debug("doAdd");
    	
    	String nurseNo = req.getParameter("nurseNo");
    	String nurseName = req.getParameter("nurseName");
    	logger.trace("nurseNo="+nurseNo);
    	logger.trace("nurseName="+nurseName);
    	
    	String to[] = req.getParameterValues("to");
    	logger.trace("to[]: {}", Arrays.asList(to));
    
    	try {
    		
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
    		
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	
        return new ModelAndView("redirect:/nurse");
    } 	
    
    /** 
     * 頁面-護士列表
     * --
     *  
     * */	
    @RequestMapping("")
    public ModelAndView nurseListPage(){
    	logger.debug("nurseListPage");
    	
    	ModelAndView modelAndView = new ModelAndView("/nurse_list");
    	
    	List<Nurse> nurseList = new ArrayList<Nurse>();
    	try {
    		nurseList = (List<Nurse>)nurseDao.findAll();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}    	
        modelAndView.addObject("nurseList", nurseList);
        logger.trace("nurseList: {}",nurseList);
        
        return modelAndView;
    }	
	
    /** 
     * 頁面-編輯護士頁
     * --
     *  
     * */    
    @RequestMapping("/edit/{nurseId}")
    public ModelAndView nurseUpdPage(@PathVariable("nurseId") String nurseId){
    	logger.debug("nurseUpdPage");
    	
    	ModelAndView modelAndView = new ModelAndView("/nurse_edit");
    	
    	logger.trace("nurseId="+nurseId);
    	modelAndView.addObject("nurseId", nurseId);
    	
    	// 護士Obj
    	Nurse nurse = nurseDao.findOne(new Long(nurseId));
        modelAndView.addObject("nurse", nurse);
        logger.trace("nurse="+nurse.toString());  
        
        // 站點護士中間表...
        List<Sitenurse> sitenurseList = sitenurseDao.findByNurseId(nurse.getNurseId());
        logger.trace("sitenurseList: {}", sitenurseList);
        
        // 已分配站點...
        List<Site> nurseSiteList = new ArrayList<Site>();
        for(Sitenurse sitenurse : sitenurseList){        	
        	nurseSiteList.add(siteDao.findOne(sitenurse.getSiteId()));
        }      
        modelAndView.addObject("nurseSiteList", nurseSiteList);
        logger.trace("已分配站點>>>nurseSiteList: {}", nurseSiteList);        
        
        // 可分配站點
        List<Site> siteList = (List<Site>)siteDao.findAll();
        if( null!=siteList && !siteList.isEmpty()
        		&& null!=nurseSiteList && !nurseSiteList.isEmpty()) {
        	siteList.removeAll(nurseSiteList);
        }
        modelAndView.addObject("siteList", siteList);
        logger.trace("可分配站點>>>siteList: {}", siteList);
        
        return modelAndView;
    }	    
	
    /** 
     * 執行-編擊-護士
     * --
     * 
     * */
    @RequestMapping(value = "/doEdit", method = RequestMethod.POST)
    public ModelAndView doEdit(HttpServletRequest req, HttpServletResponse resp) {
    	logger.debug("doEdit");
    	    	
    	String nurseId = req.getParameter("nurseId");
    	String nurseNo = req.getParameter("nurseNo");
    	String nurseName = req.getParameter("nurseName");
    	logger.trace("nurseId="+nurseId);
    	logger.trace("nurseNo="+nurseNo);
    	logger.trace("nurseName="+nurseName);
    	
    	String to[] = req.getParameterValues("to");
    	logger.trace("to[]: {}", Arrays.asList(to));   	
    	
    	// 儲存護士主擋
    	Nurse updNurse = nurseDao.findOne(new Long(nurseId));
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
        logger.trace("nowSiteIds: {}", nowSiteIds);
        
    	// 2. 取得該名護士目前選取的註冊表站點id
        List<String> newSiteIds = Arrays.asList(to);
        logger.trace("newSiteIds: {}", newSiteIds);
        
        // 3. 新增 取得該名護士 需要 新增註冊站點id
        List<String> doAddList = new ArrayList<String>();
        Set<Sitenurse> doAddSet = new HashSet<Sitenurse>();
        newSiteIds.forEach(i->{
            if (!nowSiteIds.contains(i)) { 
            	doAddList.add(i); 
            	Sitenurse newsn= new Sitenurse();
            	newsn.setSiteId(new Long(i));
            	newsn.setNurseId(new Long(nurseId));
            	newsn.setCrtDatetime(new Timestamp(System.currentTimeMillis()));
            	doAddSet.add(newsn);
            } 
        });
        if(null!=doAddSet && !doAddSet.isEmpty()) {
        	sitenurseDao.save(doAddSet);
        }
        logger.trace("doAddSiteIds: {}", doAddList);
        
        // 4. 刪除 取得該名護士 需要刪除註冊站點id
        List<String> doDelList = new ArrayList<String>();
        Set<Sitenurse> doDelSet = new HashSet<Sitenurse>();
        nowSiteIds.forEach(i->{
            if (!newSiteIds.contains(i)) { 
            	doDelList.add(i); 
            	Sitenurse delsn = sitenurseDao.findBySiteIdNurseId(new Long(i), new Long(nurseId));
            	doDelSet.add(delsn);
            }
        });       
        if(null!=doDelSet && !doDelSet.isEmpty()) {
        	sitenurseDao.delete(doDelSet);
        }
        logger.trace("doDelList: {}", doDelList);
        
        return new ModelAndView("redirect:/nurse");
    } 
    
    /** 
     * 執行-刪除-護士
     * --
     * 
     * */
    @RequestMapping("/doDel/{nurseId}")
    public ModelAndView doDel(@PathVariable("nurseId") String nurseId) {
    	logger.debug("doDel");
    	logger.trace("nurseId=", nurseId);
    	
    	List<Sitenurse> doDelSitenurses = sitenurseDao.findByNurseId((Long.parseLong(nurseId, 10)));
    	sitenurseDao.delete(doDelSitenurses);
    	
    	Nurse nurse = nurseDao.findOne(Long.parseLong(nurseId, 10));
    	nurseDao.delete(nurse);
    	
        return new ModelAndView("redirect:/nurse");
    }  	
    
}