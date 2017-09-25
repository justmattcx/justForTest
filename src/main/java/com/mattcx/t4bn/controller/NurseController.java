package com.mattcx.t4bn.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.mattcx.t4bn.model.Nurse;
import com.mattcx.t4bn.model.Site;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private NurseDao nurseDao;	
    @Autowired
    private SiteDao siteDao;	    
    
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
    	logger.trace("nurseNo=", nurseNo);
    	logger.trace("nurseName=", nurseName);
    	
    	Nurse nurse = new Nurse();
    	nurse.setNurseNo(nurseNo);
    	nurse.setNurseName(nurseName);
    	nurse.setCrtDatetime(new Timestamp(System.currentTimeMillis()));    
    	nurse.setUpdDatetime(new Timestamp(System.currentTimeMillis()));    
    	
    	String to[] = req.getParameterValues("to");
    	Set<Site> siteSet = new HashSet<Site>();
    	if(null!=to && to.length>0) {
    		for(int i=0; i<to.length; i++){
    			logger.trace(("to["+i+"]="), to[i]);

    			Long siteId = Long.parseLong(to[i], 10);
    			Site site = siteDao.findOne(siteId);
    			siteSet.add(site);
    		}
    	}  	
    	nurse.setSites(siteSet);
    	
    	try {
    		nurseDao.save(nurse);
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
    	
    	logger.trace("nurseId=", nurseId);
    	modelAndView.addObject("nurseId", nurseId);
    	
    	// 護士Obj
    	Nurse nurse = nurseDao.findOne(new Long(nurseId));
        modelAndView.addObject("nurse", nurse);
        logger.trace("nurse=", nurse.toString());  
        
        // 已分配站點
    	Set<Site> nurseSiteSet = nurse.getSites();
    	List<Site> nurseSiteList = new ArrayList<Site>();
    	nurseSiteList.addAll(nurseSiteSet);        
        modelAndView.addObject("nurseSiteList", nurseSiteList);
        logger.trace("nurseSiteList: {}", nurseSiteList);
        
        // 可分配站點
        List<Site> siteList = (List<Site>)siteDao.findAll();
        if( null!=siteList && !siteList.isEmpty()
        		&& null!=nurseSiteList && !nurseSiteList.isEmpty()) {
        	siteList.removeAll(nurseSiteList);
        }
        modelAndView.addObject("siteList", siteList);
        logger.trace("siteList: {}", siteList);
        
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
    	logger.trace("nurseId=", nurseId);
    	logger.trace("nurseNo=", nurseNo);
    	logger.trace("nurseName=", nurseName);
    	
    	String to[] = req.getParameterValues("to");
    	Set<Site> siteSet = new HashSet<Site>();
    	if(null!=to && to.length>0) {
    		for(int i=0; i<to.length; i++){
    			logger.trace(("to["+i+"]="), to[i]);
    			Site site = siteDao.findOne(Long.parseLong(to[i], 10));
    			siteSet.add(site);
    		}
    	}
    	
    	Nurse nurse = nurseDao.findOne(new Long(nurseId));
    	nurse.setNurseNo(nurseNo);
    	nurse.setNurseName(nurseName);
    	nurse.setUpdDatetime(new Timestamp(System.currentTimeMillis()));    	
    	nurse.setSites(siteSet);
    	nurseDao.save(nurse);

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
    	
    	Nurse nurse = nurseDao.findOne(Long.parseLong(nurseId, 10));
    	nurseDao.delete(nurse);
    	
        return new ModelAndView("redirect:/nurse");
    }  	
    
}