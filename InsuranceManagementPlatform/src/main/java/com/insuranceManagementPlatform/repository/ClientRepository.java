package com.insuranceManagementPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insuranceManagementPlatform.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

}
