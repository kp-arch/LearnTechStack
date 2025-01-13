package com.learn.microservice.webservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	public record HelloBeanRecord(String message) {};

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello world";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloBeanRecord helloWorldBean() {
		return new HelloBeanRecord("Hello world");
	}

	@GetMapping(path = "/hello-world-path/{name}")
	public HelloBeanRecord helloWorldPath(@PathVariable String name) {
		return new HelloBeanRecord(String.format("Hello world, %s", name));
	}
}
