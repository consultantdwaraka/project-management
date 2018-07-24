package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value="/test")
	public void test() {
		System.out.println("Test====");
	}

}
