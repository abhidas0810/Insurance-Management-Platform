package com.insuranceManagementPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceManagementPlatform.entity.InsurancePolicy;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

}
