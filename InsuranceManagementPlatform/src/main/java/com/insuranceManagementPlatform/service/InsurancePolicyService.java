package com.insuranceManagementPlatform.service;

import java.util.List;

import com.insuranceManagementPlatform.entity.InsurancePolicy;
import com.insuranceManagementPlatform.exception.ClientException;
import com.insuranceManagementPlatform.exception.InsurancePolicyException;

public interface InsurancePolicyService {

	/**Create a new insurance policy.
	 * @param emailId : to find a specific client so that insurance policy can be added to the client.
	 * @param insurancePolicy : new insurancePolicy details provided.
	 * @return : newly created insurancePolicy details.
	 * @throws InsurancePolicyException : if insurancePolicy details is not saved in database then InsurancePolicyException is throw.
	 * @throws ClientException : if client is not registered for which insurancePolicy is trying to create then clientException is throw.
	 */
	public InsurancePolicy createNewInsurancePolicy(String emailId, InsurancePolicy insurancePolicy)
			throws InsurancePolicyException, ClientException;

	/**Fetch a specific insurance policy by ID.
	 * @param policyNumber : insurance policy number to find specific insurancePolicy details.
	 * @return : insurancePolicy by using insurance policy number.
	 * @throws InsurancePolicyException : if no insurance policy is stored in the database then InsurancePolicyException is throw.
	 */
	public InsurancePolicy fetchInsurancePolicy(Integer policyNumber) throws InsurancePolicyException;

	/**Fetch all insurance policies.
	 * @return : list of all insurance policy details.
	 * @throws InsurancePolicyException : if no insurance policy is stored in the database then InsurancePolicyException is throw.
	 */
	public List<InsurancePolicy> fetchInsurancePolicies() throws InsurancePolicyException;

	/**Update an insurance policy.
	 * @param policyNumber : to find specific insurancePolicy details.
	 * @param insurancePolicy : updated details of insurancePolicy.
	 * @return : updated insurancePolicy details of existing insurancePolicy.
	 * @throws InsurancePolicyException : if no insurance policy is stored in the database which is required to update then InsurancePolicyException is throw. 
	 */
	public InsurancePolicy updateInsurancePolicy(Integer policyNumber, InsurancePolicy insurancePolicy)
			throws InsurancePolicyException;

	/**Delete an insurance policy.
	 * @param policyNumber : to find specific insurancePolicy details.
	 * @return : details of deleted insurancePolicy.
	 * @throws InsurancePolicyException : if no insurance policy is stored in the database which is required to delete then InsurancePolicyException is throw. 
	 */
	public InsurancePolicy deleteInsurancePolicy(Integer policyNumber) throws InsurancePolicyException;

}
