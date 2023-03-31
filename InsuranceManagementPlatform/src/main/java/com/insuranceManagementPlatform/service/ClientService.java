package com.insuranceManagementPlatform.service;

import java.util.List;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.exception.ClientException;

public interface ClientService {

	/**
	 * Create a new client.
	 * @param client : details of client passed.
	 * @return : new registered client's information.
	 * @throws ClientException : if client is not registered successfully ClientException is thrown.
	 */
	public Client registerClient(Client client) throws ClientException;

	/**
	 * Fetch a specific client by ID.
	 * @param emailId : emailId of client is passed to find the specific client by ID.
	 * @return : specific client which is found by ID.
	 * @throws ClientException : if existing client is not found successfully ClientException is thrown.
	 */
	public Client getClientById(String emailId) throws ClientException;

	/**
	 * Fetch all clients. 
	 * @return : list of all clients details.
	 * @throws ClientException : if no client is found ClientException is thrown.
	 */
	public List<Client> getAllClients() throws ClientException;

	/**
	 * Update a client's information.
	 * @param emailId : emailId of client is passed to find the specific client is registered or not by ID.
	 * @param client : updated details of client passed.
	 * @return : updated client details.
	 * @throws ClientException : if client is not updated successfully ClientException is thrown.
	 */
	public Client updateClient(String emailId, Client client) throws ClientException;

	/**
	 * Delete a client.
	 * @param emailId : emailId of client is passed to find the specific client which we want to delete.
	 * @return : deleted client details.
	 * @throws ClientException : if client is not deleted successfully ClientException is thrown.
	 */
	public Client deleteClient(String emailId) throws ClientException;

}
