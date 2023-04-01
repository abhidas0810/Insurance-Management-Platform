package com.insuranceManagementPlatform.service;

import java.util.List;

import com.insuranceManagementPlatform.entity.Claim;
import com.insuranceManagementPlatform.exception.ClaimException;
import com.insuranceManagementPlatform.exception.InsurancePolicyException;

public interface ClaimService {

	/**Create a new claim.
	 * @param policyNumber : to find a specific insurancePolicy so that claim can be added to the insurancePolicy.
	 * @param claim : new claim details.
	 * @return : newly created claim for a particular insurance policy.
	 * @throws ClaimException : if claim details is not saved in database then ClaimException is throw.
	 * @throws InsurancePolicyException : if insurancePolicy is not registered for which claim is trying to create then InsurancePolicyException is throw.
	 */
	public Claim createNewClaim(Integer policyNumber, Claim claim) throws ClaimException, InsurancePolicyException;

	/**Fetch a specific claim by ID.
	 * @param claimNumber : to find claim details.
	 * @return : claim by using claimNumber.
	 * @throws ClaimException : if claim is not found for specific claimNumber then ClaimException is thrown.
	 */
	public Claim fetchClaim(Integer claimNumber) throws ClaimException;

	/**Fetch all claims.
	 * @return : list of all claims.
	 * @throws ClaimException : if no claim is found then ClaimException is thrown.
	 */
	public List<Claim> fetchClaims() throws ClaimException;

	/**Update a claim's information.
	 * @param claimNumber : to find claim details.
	 * @param claim : updated details of claim.
	 * @return : updated details with existing claim details.
	 * @throws ClaimException : if no claim is found to update then ClaimException is thrown.
	 */
	public Claim updateClaim(Integer claimNumber, Claim claim) throws ClaimException;

	/**Delete a claim.
	 * @param claimNumber : to find claim details.
	 * @return : details of deleted claim.
	 * @throws ClaimException : if no claim is found to delete then ClaimException is thrown.
	 */
	public Claim deleteClaim(Integer claimNumber) throws ClaimException;

}
