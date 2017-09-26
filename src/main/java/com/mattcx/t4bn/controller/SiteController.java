package com.mattcx.t4bn.controller;

import java.util.List;

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

import com.mattcx.t4bn.model.Site;
import com.mattcx.t4bn.model.Sitenurse;
import com.mattcx.t4bn.service.SiteService;

@RestController
@RequestMapping("/site")
public class SiteController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	SiteService siteService;

	/** 
     * 頁面-新增站點頁
     * --
     *  
     * */
    @RequestMapping("/add")
    public ModelAndView siteAddPage(){
    	logger.debug("siteAddPage");
    	
        ModelAndView modelAndView = new ModelAndView("/site_new");
        return modelAndView;
    }		
	
    /** 
     * 執行-新增-站點 
     * --
     * 
     * */
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest req, HttpServletResponse resp) {        
    	logger.debug("doAdd");
    	String siteName = req.getParameter("siteName");
    	logger.trace("siteName="+siteName);

    	siteService.doAddSite(siteName);
        return new ModelAndView("redirect:/site");
    }  	
	
    /** 
     * 頁面-站點列表
     * --
     *  
     * */
    @RequestMapping("")
    public ModelAndView siteListPage(){
    	logger.debug("siteListPage");
    	List<Site> siteList = siteService.findAll();
    	
        ModelAndView modelAndView = new ModelAndView("/site_list");
        modelAndView.addObject("siteList", siteList);
        return modelAndView;
    }	
	    
    /** 
     * 頁面-編輯站點頁
     * --
     *  
     * */
    @RequestMapping("/edit/{siteId}")
    public ModelAndView siteUpdPage(@PathVariable("siteId") String siteId){
    	logger.debug("siteUpdPage");
    	logger.trace("siteId="+siteId);
    	
    	ModelAndView modelAndView = new ModelAndView("/site_edit");
    	modelAndView.addObject("siteId",siteId);
    	
    	Site site = siteService.findOne(new Long(siteId));
        modelAndView.addObject("site", site);
   
        List<Sitenurse> showSitenurseList = siteService.findSiteNurseListBySiteId(new Long(siteId));
        modelAndView.addObject("showSitenurseList", showSitenurseList);
        
        return modelAndView;
    }	    
    
    /** 
     * 執行-編擊-站點 
     * --
     * 
     * */
    @RequestMapping(value = "/doEdit", method = RequestMethod.POST)
    public ModelAndView doEdit(HttpServletRequest req, HttpServletResponse resp) {
    	logger.debug("doEdit");
    	
    	String siteId = req.getParameter("siteId");
    	String siteName = req.getParameter("siteName");
    	logger.trace("siteId="+siteId);    
    	logger.trace("siteName="+siteName);    
    	
    	siteService.editSite(new Long(siteId), siteName);
        return new ModelAndView("redirect:/site");
    } 
    
    /** 
     * 執行-刪除-站點 
     * --
     * 
     * */
    @RequestMapping("/doDel/{siteId}")
    public ModelAndView doDel(@PathVariable("siteId") String siteId) {
    	logger.debug("doDel");
    	logger.trace("siteId="+siteId);  
    	
    	siteService.deleteSiteInfo(Long.parseLong(siteId, 10));
        return new ModelAndView("redirect:/site");
    }       
    
}
