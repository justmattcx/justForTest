package com.mattcx.t4bn.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/main")
public class MainController {

    @RequestMapping("")
    public ModelAndView mainPage(){

        System.out.println("run: mainPage>>>main.jsp");
    	
        ModelAndView modelAndView = new ModelAndView("/main");
        //modelAndView.addObject("justTestString", "");
        
        return modelAndView;
    }
	
}
