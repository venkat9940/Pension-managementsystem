package com.pensionManagementSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pensionManagementSystem.model.PensionDetail;

@Repository
public interface PensionRepo extends JpaRepository<PensionDetail, String> {

}
