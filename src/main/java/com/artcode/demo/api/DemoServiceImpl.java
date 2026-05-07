package com.artcode.demo.api;

import java.util.Locale;

import org.springframework.stereotype.Service;

import com.artcode.demo.api.crud.User;
import com.artcode.demo.api.crud.UserRepository;
import com.artcode.demo.api.dto.RequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

	private static final int MAX_LOCAL_PART_LENGTH = 20;
	private static final String DOMAIN = "domain.com";

	private final UserRepository userRepository;

	@Override
	public String getEmail(RequestDto requestDto) {
		log.info("Generating email address for incoming request");

		String firstName = normalizeName(requestDto.getFirstName(), "firstName");
		String lastName = normalizeName(requestDto.getLastName(), "lastName");

		String email = buildEmail(firstName, lastName);
		log.info("Generated email address: {}", email);

		if (userRepository.existsByEmailIgnoreCase(email)) {
			log.warn("User with email {} already exists", email);
			return email;
		}

		User user = new User();
		user.setName(firstName + " " + lastName);
		user.setEmail(email);
		user.setPassword(createUniquePassword(firstName, lastName));
		userRepository.save(user);

		return email;
	}

	private String normalizeName(String value, String fieldName) {
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException(fieldName + " must not be blank");
		}
		return value.trim().toLowerCase(Locale.ROOT);
	}

	private String buildEmail(String firstName, String lastName) {
		String localPart = firstName + "." + lastName;
		if (localPart.length() > MAX_LOCAL_PART_LENGTH) {
			localPart = truncateLocalPart(firstName, lastName);
		}
		return localPart + "@" + DOMAIN;
	}

	private String truncateLocalPart(String firstName, String lastName) {
		int totalLength = firstName.length() + lastName.length();
		int extraLength = totalLength - MAX_LOCAL_PART_LENGTH;

		if (lastName.length() > extraLength) {
			lastName = lastName.substring(0, lastName.length() - extraLength);
		} else if (firstName.length() > extraLength) {
			firstName = firstName.substring(0, firstName.length() - extraLength);
		}

		return firstName + "." + lastName;
	}

	private String createUniquePassword(String firstName, String lastName) {
		return firstName + lastName + System.currentTimeMillis();
	}


}
