package com.mattcx.t4bn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/main")
public class MainController {

    @RequestMapping("")
    public ModelAndView index(){
    	List<List<String>> learnList =new ArrayList<List<String>>();
    	
    	List<String> bean1 = new ArrayList<String>();
        learnList.add(bean1);

    	List<String> bean2 = new ArrayList<String>();
           learnList.add(bean2);       
        
        
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("learnList", learnList);
        return modelAndView;
    }
	
}
