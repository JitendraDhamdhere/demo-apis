package com.artcode.demo.api;

import org.springframework.stereotype.Service;

import com.artcode.demo.api.dto.RequestDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

	private final String DOMAIN = "domain.com";

	@Override
	public String getEmail(RequestDto requestDto) {
		log.info("In Service start business logic-");
		String firstName = requestDto.getFirstName().toLowerCase();

		String lastName = requestDto.getLastName().toLowerCase();

		if (firstName.length() + lastName.length() > 20) {
			int firstNameLength = firstName.length();
			int lastNameLength = lastName.length();

			int extraLength = (firstNameLength + lastNameLength) - 20;

			if (lastNameLength > extraLength) {
				lastName = lastName.substring(0, lastNameLength - extraLength);
			} else if (firstNameLength > extraLength) {
				firstName = firstName.substring(0, firstNameLength - extraLength);
			}
		}

		String email = firstName + "." + lastName + "@" + DOMAIN;
		log.info("In Service - completed mail created : " + email);

		return email;
	}

}
