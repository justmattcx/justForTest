package com.mattcx.t4bn.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.mattcx.t4bn.model.Nurse;
import com.mattcx.t4bn.model.Site;
import com.mattcx.t4bn.service.NurseService;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NurseService nurseService;	
	    
    /** 
     * 頁面-新增站點頁
     * --
     *  
     * */
    @RequestMapping("/add")
    public ModelAndView nueseAddPage(){
    	logger.debug("nueseAddPage");
    	
        ModelAndView modelAndView = new ModelAndView("/nurse_new");
        List<Site> siteList =nurseService.findAllSite();
        modelAndView.addObject("siteList", siteList);        
       
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
    
    	nurseService.doAddNurse(nurseNo, nurseName, to);
    	
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
    	
    	List<Nurse> nurseList = nurseService.findAllNurse();
        modelAndView.addObject("nurseList", nurseList);
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
    	Nurse nurse = nurseService.findNurseByNurseId(new Long(nurseId));
        modelAndView.addObject("nurse", nurse);
        
        // 取得 已分配站點清單/可再分配站點清單
        Map<String, List<Site>> sitenurseMap = new HashMap<String, List<Site>>();
        sitenurseMap = nurseService.findSitenurseMap_editPage(nurse.getNurseId());
        // 已分配站點...
        List<Site> nurseSiteList = sitenurseMap.get("nurseSiteList");    
        modelAndView.addObject("nurseSiteList", nurseSiteList);
        // 可分配站點
        List<Site> siteList = sitenurseMap.get("siteList");   
        modelAndView.addObject("siteList", siteList);
        
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
    	
    	// 儲存護士主擋(修改)+儲存護士註冊站點檔(新增+刪除)
    	nurseService.saveNurseInfo(new Long(nurseId), nurseNo, nurseName, to);
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
    	logger.trace("nurseId="+nurseId);
    	
    	nurseService.deleteSiteInfo(Long.parseLong(nurseId, 10));	
        return new ModelAndView("redirect:/nurse");
    }  	
}