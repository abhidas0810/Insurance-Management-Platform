package com.insuranceManagementPlatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insuranceManagementPlatform.entity.Client;
import com.insuranceManagementPlatform.entity.InsurancePolicy;
import com.insuranceManagementPlatform.exception.ClientException;
import com.insuranceManagementPlatform.exception.InsurancePolicyException;
import com.insuranceManagementPlatform.repository.ClientRepository;
import com.insuranceManagementPlatform.repository.InsurancePolicyRepository;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

	/**
	 * insurancePolicyRepository : Dependency of InsurancePolicyRepository.
	 */
	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;

	/**
	 * clientRepository : Dependency of ClientRepository.
	 */
	@Autowired
	private ClientRepository clientRepository;

	/**
	 * Create a new insurance policy.
	 */
	@Override
	public InsurancePolicy createNewInsurancePolicy(String emailId, InsurancePolicy insurancePolicy)
			throws InsurancePolicyException, ClientException {
		Client client = clientRepository.findById(emailId)
				.orElseThrow(() -> new ClientException("client not registered whose policy you want to create."));
		insurancePolicy.setClient(client);
		client.getInsurancePolicies().add(insurancePolicy);
		InsurancePolicy registeredInsurancePolicy = insurancePolicyRepository.save(insurancePolicy);
		if (registeredInsurancePolicy == null) {
			throw new InsurancePolicyException("Insurance policy can not be created.");
		}
		return registeredInsurancePolicy;
	}

	/**
	 * Fetch a specific insurance policy by ID.
	 */
	@Override
	public InsurancePolicy fetchInsurancePolicy(Integer policyNumber) throws InsurancePolicyException {
		return insurancePolicyRepository.findById(policyNumber).orElseThrow(
				() -> new InsurancePolicyException("Insurance Policy not found with policyNumber " + policyNumber));
	}

	/**
	 * Fetch all insurance policies.
	 */
	@Override
	public List<InsurancePolicy> fetchInsurancePolicies() throws InsurancePolicyException {
		List<InsurancePolicy> insurancePolicies = insurancePolicyRepository.findAll();
		if (insurancePolicies.isEmpty()) {
			throw new InsurancePolicyException("No insurance policy found.");
		}
		return insurancePolicies;
	}

	/**
	 * Update an insurance policy.
	 */
	@Override
	public InsurancePolicy updateInsurancePolicy(Integer policyNumber, InsurancePolicy insurancePolicy)
			throws InsurancePolicyException {
		InsurancePolicy registeredInsurancePolicy = insurancePolicyRepository.findById(policyNumber).orElseThrow(
				() -> new InsurancePolicyException("Insurance Policy not found with policyNumber " + policyNumber));
		registeredInsurancePolicy.setClient(insurancePolicy.getClient());
		registeredInsurancePolicy.setCoverageAmount(insurancePolicy.getCoverageAmount());
		registeredInsurancePolicy.setEndDate(insurancePolicy.getEndDate());
		registeredInsurancePolicy.setPremium(insurancePolicy.getPremium());
		registeredInsurancePolicy.setStartDate(insurancePolicy.getStartDate());
		registeredInsurancePolicy.setType(insurancePolicy.getType());
		registeredInsurancePolicy.getClaims().addAll(insurancePolicy.getClaims());
		return insurancePolicyRepository.save(registeredInsurancePolicy);
	}

	/**
	 * Delete an insurance policy.
	 */
	@Override
	public InsurancePolicy deleteInsurancePolicy(Integer policyNumber) throws InsurancePolicyException {
		InsurancePolicy registeredInsurancePolicy = insurancePolicyRepository.findById(policyNumber).orElseThrow(
				() -> new InsurancePolicyException("Insurance Policy not found with policyNumber " + policyNumber));
		Client client = clientRepository.findById(registeredInsurancePolicy.getClient().getEmailId()).orElseThrow(
				() -> new InsurancePolicyException("client not registered whose policy you want to create."));
		client.getInsurancePolicies().remove(registeredInsurancePolicy);
		insurancePolicyRepository.delete(registeredInsurancePolicy);
		return registeredInsurancePolicy;
	}

}
