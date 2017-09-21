package com.mattcx.t4bn.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

	@RequestMapping("/")
	String main() {
		return "Hello World!";
	}
	
}
