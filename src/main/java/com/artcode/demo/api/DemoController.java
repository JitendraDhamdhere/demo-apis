package com.artcode.demo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.artcode.demo.api.dto.RequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DemoController {

	private final DemoService demoService;

	@GetMapping("/getMail")
	public String getMethodName(@ModelAttribute RequestDto requestDto) {
		log.info("Request received for email generation: {}", requestDto);
		return demoService.getEmail(requestDto);
	}

}
