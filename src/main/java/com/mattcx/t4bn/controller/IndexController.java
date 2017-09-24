package com.mattcx.t4bn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("")
public class IndexController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /** 
     * 頁面-入口點
     * --
     *  
     * */	
    @RequestMapping("")
    public ModelAndView indexPage(){ 	
    	logger.debug("mainPage");
    	// logger.trace("mainPage");
    	// logger.debug("mainPage");
    	// logger.info("mainPage");
    	// logger.warn("mainPage");
    	// logger.error("mainPage");
    	return new ModelAndView("redirect:/main");
    }
	
}