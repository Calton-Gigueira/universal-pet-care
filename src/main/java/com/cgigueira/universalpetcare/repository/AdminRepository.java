package com.cgigueira.universalpetcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgigueira.universalpetcare.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
