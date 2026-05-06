package com.artcode.demo;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artcode.demo.dto.RequestDto;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final String DOMAIN = "domain.com";

	@GetMapping("/getMail")
	public String getMethodName(@ParameterObject RequestDto requestDto) {

		String firstName = requestDto.getFirstName();

		String lastName = requestDto.getLastName();

		if (firstName.length() + lastName.length() > 20) {
			int firstNameLength = firstName.length();
			int lastNameLength = lastName.length();

			int extraLength = (firstNameLength + lastNameLength) - 20;

			if (lastNameLength > extraLength) {
				lastName = lastName.substring(0, lastNameLength - extraLength);
			} else if(firstNameLength > extraLength) {
				firstName = firstName.substring(0, firstNameLength - extraLength);
			}
		}

		String email = firstName + "." + lastName + "@" + DOMAIN;

		return email;
	}

}
