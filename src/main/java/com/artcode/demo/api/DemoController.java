package com.artcode.demo.api;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artcode.demo.api.dto.RequestDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DemoController {

	@Autowired
	private DemoService demoService;

	@GetMapping("/getMail")
	public String getMethodName(@ParameterObject RequestDto requestDto) {
		log.info("Request received for email with parameters: " + requestDto);
		return demoService.getEmail(requestDto);
	}

}
