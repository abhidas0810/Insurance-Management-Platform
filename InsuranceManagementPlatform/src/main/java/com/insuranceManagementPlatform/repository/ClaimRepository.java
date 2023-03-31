package com.insuranceManagementPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceManagementPlatform.entity.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

}
