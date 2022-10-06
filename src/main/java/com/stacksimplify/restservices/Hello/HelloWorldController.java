package com.stacksimplify.restservices.Hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController 
public class HelloWorldController {

	//Simple Method
	//URI - /helloworld
	//GET	
	//
	@RequestMapping(method = RequestMethod.GET, path = "/helloworld")
	public String helloWorld() {
		return "Hello";
	}
	
}
