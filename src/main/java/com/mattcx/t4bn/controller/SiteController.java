package com.mattcx.t4bn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mattcx.t4bn.dao.SiteInfoDao;

@RestController
@RequestMapping("/site")
public class SiteController {


	
//	  @Autowired
//	  private SiteInfoDao siteInfoDao;
	

	/** 
     * 頁面-新增站點頁
     * --
     *  
     * */
    @RequestMapping("/add")
    public ModelAndView siteAddPage(){
    	
    	System.out.println("run: siteAddPage");
    	
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
        
    	System.out.println("run: doAdd");
    	
    	String siteName = req.getParameter("siteName");
    	System.out.println("doAdd: siteName>>>"+siteName);
    	
        return new ModelAndView("redirect:/site");
    }  	
	
    /** 
     * 頁面-站點列表
     * --
     *  
     * */
    @RequestMapping("")
    public ModelAndView siteListPage(){
              
    	System.out.println("run: siteListPage");
    	
        List<Map<String, Object>> siteList = new ArrayList<Map<String, Object>>();
        
        Map<String, Object> siteMap1 = new HashMap<String, Object>();
        siteMap1.put("siteId", "55");
        siteMap1.put("siteName", "A0001");
        siteMap1.put("updDate", "2017/09/20 10:55:20");
        siteList.add(siteMap1);
        
        Map<String, Object> siteMap2 = new HashMap<String, Object>();
        siteMap2.put("siteId", "67");
        siteMap2.put("siteName", "A0002");
        siteMap2.put("updDate", "2017/09/20 10:55:20");        
        siteList.add(siteMap2);
        
        ModelAndView modelAndView = new ModelAndView("/site_list");
        modelAndView.addObject("siteList", siteList);
        
       //List siteInfos = siteInfoDao.getAll(); 
        
        
       // System.out.println("siteInfos>>>"+siteInfos);
        
        return modelAndView;
    }	
	    
    /** 
     * 頁面-編輯站點頁
     * --
     *  
     * */
    @RequestMapping("/edit/{siteId}")
    public ModelAndView siteUpdPage(@PathVariable("siteId") String siteId){
    	
    	System.out.println("run: siteUpdPage");
    	System.out.println("siteId>>>"+siteId);
        
        ModelAndView modelAndView = new ModelAndView("/site_edit");
        modelAndView.addObject("siteId", siteId);
        
        return modelAndView;
    }	    
    
    /** 
     * 執行-編擊-站點 
     * --
     * 
     * */
    @RequestMapping(value = "/doEdit", method = RequestMethod.POST)
    public ModelAndView doEdit(HttpServletRequest req, HttpServletResponse resp) {
        
    	System.out.println("run: doEdit");
    	
    	String siteId = req.getParameter("siteId");
    	String siteName = req.getParameter("siteName");
    	System.out.println("doEdit: siteId>>>"+siteId);
    	System.out.println("doEdit: siteName>>>"+siteName);
    	
        return new ModelAndView("redirect:/site");
    } 
    
    /** 
     * 執行-刪除-站點 
     * --
     * 
     * */
    @RequestMapping("/doDel/{siteId}")
    public ModelAndView doDel(@PathVariable("siteId") String siteId) {
        
    	System.out.println("run: doDel");
    	System.out.println("doDel: siteId>>>"+siteId);
    	
        return new ModelAndView("redirect:/site");
    }       
    
}
