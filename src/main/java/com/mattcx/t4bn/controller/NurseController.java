package com.mattcx.t4bn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mattcx.t4bn.dao.NurseDao;
import com.mattcx.t4bn.model.Nurse;
import com.mattcx.t4bn.model.Site;

@RestController
@RequestMapping("/nurse")
public class NurseController {
	
    @Autowired
    private NurseDao nurseDao;	
	
	
    /** 
     * 頁面-新增站點頁
     * --
     *  
     * */
    @RequestMapping("/add")
    public ModelAndView nueseAddPage(){
    	
    	System.out.println("run: nueseAddPage");
        
        ModelAndView modelAndView = new ModelAndView("/nurse_new");
        return modelAndView;
    }		
	
    /** 
     * 執行-新增-護士
     * --
     * 
     * */
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    public ModelAndView doAdd(HttpServletRequest req, HttpServletResponse resp) {
        
    	System.out.println("run: doAdd");
    	
    	String nurseNo = req.getParameter("nurseNo");
    	String nurseName = req.getParameter("nurseName");
    	System.out.println("doAdd: nurseNo>>>"+nurseNo);
    	System.out.println("doAdd: nurseName>>>"+nurseName);
    	
    	Nurse nurse = new Nurse();
    	nurse.setNurseNo(nurseNo);
    	nurse.setNurseName(nurseName);
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
    	
    	System.out.println("run: nurseListPage");
    	
    	List<Nurse> nurseList = (List<Nurse>)nurseDao.findAll();
    	   
        ModelAndView modelAndView = new ModelAndView("/nurse_list");
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
    	
    	System.out.println("run: nurseUpdPage");
    	System.out.println("nurseId>>>"+nurseId);
        
    	Nurse nurse = nurseDao.findOne(new Long(nurseId));
    	
        ModelAndView modelAndView = new ModelAndView("/nurse_edit");
        modelAndView.addObject("nurseId", nurseId);
        modelAndView.addObject("nurse", nurse);
        
        return modelAndView;
    }	    
	
    /** 
     * 執行-編擊-護士
     * --
     * 
     * */
    @RequestMapping(value = "/doEdit", method = RequestMethod.POST)
    public ModelAndView doEdit(HttpServletRequest req, HttpServletResponse resp) {
        
    	System.out.println("run: doEdit");
    	
    	String nurseId = req.getParameter("nurseId");
    	String nurseNo = req.getParameter("nurseNo");
    	String nurseName = req.getParameter("nurseName");
    	
    	System.out.println("doEdit: nurseId>>>"+nurseId);
    	System.out.println("doEdit: nurseNo>>>"+nurseNo);
    	System.out.println("doEdit: nurseName>>>"+nurseName);   
    	
    	Nurse nurse = new Nurse();
    	nurse.setNurseId(new Long(nurseId));
    	nurse.setNurseNo(nurseNo);
    	nurse.setNurseName(nurseName);
    	//nurse.setUpdDatetime(new Timestamp(System.currentTimeMillis()));    	
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
        
    	System.out.println("run: doDel");
    	System.out.println("doDel: nurseId>>>"+nurseId);
    	
    	nurseDao.delete(new Long(nurseId));
    	
        return new ModelAndView("redirect:/nurse");
    }  	
    
}
