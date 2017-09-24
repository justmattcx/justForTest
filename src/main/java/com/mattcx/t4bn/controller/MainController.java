package com.mattcx.t4bn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/main")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    /** 
     * 頁面-首頁
     * --
     *  
     * */	
    @RequestMapping("")
    public ModelAndView mainPage(){ 	
    	logger.debug("mainPage");
//    	logger.trace("mainPage");
//    	logger.debug("mainPage");
//    	logger.info("mainPage");
//    	logger.warn("mainPage");
//    	logger.error("mainPage");

        ModelAndView modelAndView = new ModelAndView("/main");
        return modelAndView;
    }
	
}