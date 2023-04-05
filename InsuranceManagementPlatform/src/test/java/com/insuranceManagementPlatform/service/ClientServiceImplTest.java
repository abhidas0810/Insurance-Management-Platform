package com.insuranceManagementPlatform.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.exception.ClientException;
import com.insuranceManagementPlatform.repository.ClientRepository;

@SpringBootTest
public class ClientServiceImplTest {

	@InjectMocks
	private ClientServiceImpl clientServiceImpl;

	@Mock
	private ClientRepository clientRepository;

	private Optional<Client> createClientStub() {
		Client client = new Client();
		client.setEmailId("abhi@gmail.com");
		client.setName("abhishek");
		client.setAddress("kolkata");
		client.setDateOfBirth(new Date(2000 - 02 - 26));
		client.setMobileNumber("1234567890");
		client.setPassword("Abhishek@123");
		return Optional.of(client);
	}

	@Test
	public void registerClient() {
		when(clientRepository.save(createClientStub().get())).thenReturn(createClientStub().get());
		
		try {
			Client savedClient = clientServiceImpl.registerClient(createClientStub().get());
			assertEquals(savedClient.getEmailId(), "abhi@gmail.com");
			assertEquals(savedClient.getAddress(), "kolkata");
			assertEquals(savedClient.getMobileNumber(), "1234567890");
			assertEquals(savedClient.getName(), "abhishek");
			assertEquals(savedClient.getPassword(), "Abhishek@123");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getClientByIdTest() {
		when(clientRepository.findById("abhi@gmail.com")).thenReturn(createClientStub());
		
		try {
			Client client = clientServiceImpl.getClientById("abhi@gmail.com");
			assertEquals(client.getEmailId(), "abhi@gmail.com");
			assertEquals(client.getAddress(), "kolkata");
			assertEquals(client.getMobileNumber(), "1234567890");
			assertEquals(client.getName(), "abhishek");
			assertEquals(client.getPassword(), "Abhishek@123");
		} catch (ClientException e) {			
			e.printStackTrace();
		}
 	}

	@Test
	public void getAllClients() {
		List<Client> clients = new ArrayList<>();
		clients.add(createClientStub().get());
		clients.add(createClientStub().get());

		when(clientRepository.findAll()).thenReturn(clients);

		try {
			List<Client> allClients = clientServiceImpl.getAllClients();
			assertEquals(allClients.size(), clients.size());
			assertEquals(allClients.get(0).getEmailId(), "abhi@gmail.com");
			assertEquals(allClients.get(1).getMobileNumber(), "1234567890");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void updateClientTest() {
		Client update = createClientStub().get();
		update.setAddress("Delhi");
		update.setMobileNumber("0123456789");
		when(clientRepository.findById("abhi@gmail.com")).thenReturn(createClientStub());
		when(clientRepository.save(update)).thenReturn(update);

		try {
			Client updatedClient = clientServiceImpl.updateClient("abhi@gmail.com", update);
			assertEquals(updatedClient.getAddress(), update.getAddress());
			assertEquals(updatedClient.getMobileNumber(), update.getMobileNumber());
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteClient() {
		
		when(clientRepository.findById("abhi@gmail.com")).thenReturn(createClientStub());
		try {
			Client client = clientServiceImpl.deleteClient("abhi@gmail.com");
			assertEquals(client.getEmailId(), "abhi@gmail.com");
			assertEquals(client.getAddress(), "kolkata");
			assertEquals(client.getMobileNumber(), "1234567890");
			assertEquals(client.getName(), "abhishek");
			assertEquals(client.getPassword(), "Abhishek@123");		
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
