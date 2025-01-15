package com.learn.microservices.limits.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.microservices.limits.bean.Limits;
import com.learn.microservices.limits.configuration.LimitsConfiguration;

@RestController
public class LimitsController {

	@Autowired
	private LimitsConfiguration limitConfiguration;

	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(limitConfiguration.getMinimum(), limitConfiguration.getMaximum()); 
	}
}
