package com.insuranceManagementPlatform.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.repository.ClientRepository;

@Service
public class CustomClientDetailsService implements UserDetailsService {

	/**
	 * clientRepository : dependency of ClientRepository.
	 */
	@Autowired
	private ClientRepository clientRepository;

	/**
	 * @param emailId : emailId of client is passed to find the specific client by ID.
	 * @return : UserDetails of authorized client.
	 */
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		Client registeredClient = clientRepository.findById(emailId)
				.orElseThrow(() -> new UsernameNotFoundException("Client not found with emailId " + emailId));

		List<GrantedAuthority> authorities = new ArrayList<>();

		return new User(registeredClient.getEmailId(), registeredClient.getPassword(), authorities);

	}

}
