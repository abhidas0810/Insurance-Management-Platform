package com.insuranceManagementPlatform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.exception.ClientException;
import com.insuranceManagementPlatform.service.ClientService;

@RestController
@RequestMapping("/api")
public class ClientController {

	/**
	 * clientService : dependency of ClientService.
	 */
	@Autowired
	private ClientService clientService;

	/**
	 * passwordEncoder : dependency of PasswordEncoder.
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * taking the request from the user and registering to the database by taking
	 * all the data.
	 */
	@PostMapping("/clients")
	public ResponseEntity<Client> registerClientHandler(@RequestBody Client client) throws ClientException {
		client.setPassword(passwordEncoder.encode(client.getPassword()));
		return new ResponseEntity<Client>(clientService.registerClient(client), HttpStatus.CREATED);
	}

	/**
	 * taking the request from the user and returning client details of a specific
	 * id.
	 */
	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientByIdHandler(@PathVariable("id") String emailId) throws ClientException {
		return new ResponseEntity<Client>(clientService.getClientById(emailId), HttpStatus.OK);
	}

	/**
	 * taking the request from the user and returning all client details.
	 */
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClientsHandler() throws ClientException {
		return new ResponseEntity<List<Client>>(clientService.getAllClients(), HttpStatus.OK);
	}

	/**
	 * taking the request from the user and returning updated client details of a
	 * specific id.
	 */
	@PutMapping("/clients/{id}")
	public ResponseEntity<Client> updateClientHandler(@PathVariable("id") String emailId, @RequestBody Client client)
			throws ClientException {
		return new ResponseEntity<Client>(clientService.updateClient(emailId, client), HttpStatus.ACCEPTED);
	}

	/**
	 * taking the request from the user and returning client details of a specific
	 * id after deleting from database.
	 */
	@DeleteMapping("/clients/{id}")
	public ResponseEntity<Client> deleteClientHandler(@PathVariable("id") String emailId) throws ClientException {
		return new ResponseEntity<Client>(clientService.deleteClient(emailId), HttpStatus.OK);
	}

}
