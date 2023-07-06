package com.zonesoft.hello.controllers;


import java.text.MessageFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@GetMapping(value={"","/","/greeting"})
	@ResponseBody
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {		
		return MessageFormat.format("Hi there {0}. This message is from HomeController.greeting",name);
	}


	@GetMapping(value={"/hello"})
	@ResponseBody
	public String hello() {		
		return "Hi there. This message is from HomeController.hello";
	}		
}