package com.insuranceManagementPlatform.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.exception.ClientException;
import com.insuranceManagementPlatform.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	/**
	 * clientRepository : dependency of clientRepository.
	 */
	@Autowired
	private ClientRepository clientRepository;

	/**
	 * Create a new client implementation.
	 */
	@Override
	public Client registerClient(Client client) throws ClientException {
		Optional<Client> userOptional = clientRepository.findById(client.getEmailId());
		if (userOptional.isPresent()) {
			throw new ClientException("Client already registered with emailId " + client.getEmailId());
		}
		Client registeredClient = clientRepository.save(client);
		if (registeredClient != null) {
			return registeredClient;
		} else {
			throw new ClientException("Client not registred.");
		}
	}

	/**
	 * Fetch a specific client by ID.
	 */
	@Override
	public Client getClientById(String emailId) throws ClientException {
		return clientRepository.findById(emailId)
				.orElseThrow(() -> new ClientException("Client not found with emailId " + emailId));
	}

	/**
	 * Fetch all clients.
	 */
	@Override
	public List<Client> getAllClients() throws ClientException {
		List<Client> clients = clientRepository.findAll();
		if (clients.isEmpty()) {
			throw new ClientException("No client found.");
		}
		return clients;
	}

	/**
	 * Update a client's information.
	 */
	@Override
	public Client updateClient(String emailId, Client client) throws ClientException {		
		Client registeredClient = clientRepository.findById(emailId)
				.orElseThrow(() -> new ClientException("Client not found with emailId " + emailId));
		registeredClient.setAddress(client.getAddress());
		registeredClient.setDateOfBirth(client.getDateOfBirth());
		registeredClient.setMobileNumber(client.getMobileNumber());
		registeredClient.setName(client.getName());
		registeredClient.setPassword(client.getPassword());
		registeredClient.getInsurancePolicies().addAll(client.getInsurancePolicies());
		return clientRepository.save(registeredClient);
	}

	/**
	 * Delete a client.
	 */
	@Override
	public Client deleteClient(String emailId) throws ClientException {
		Client registeredClient = clientRepository.findById(emailId)
				.orElseThrow(() -> new ClientException("Client not found with emailId " + emailId));
		clientRepository.delete(registeredClient);
		return registeredClient;
	}

}
