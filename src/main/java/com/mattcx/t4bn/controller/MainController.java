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
    
        ModelAndView modelAndView = new ModelAndView("/main2");
        
        return modelAndView;
    }
	
}
