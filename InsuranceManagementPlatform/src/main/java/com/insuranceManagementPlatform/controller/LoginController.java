package com.insuranceManagementPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.repository.ClientRepository;

@RestController
@RequestMapping("/api")
public class LoginController {

	/**
	 * clientRepository : dependency of ClientRepository.
	 */
	@Autowired
	private ClientRepository clientRepository;

	/**
	 * @param auth : authentication request.
	 * @return : authorized client details.
	 */
	@GetMapping("/logIn")
	public ResponseEntity<Client> getLoggedInCustomerDetailsHandler(Authentication auth) {

		Client client = clientRepository.findById(auth.getName())
				.orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));

		return new ResponseEntity<>(client, HttpStatus.ACCEPTED);
	}
}
