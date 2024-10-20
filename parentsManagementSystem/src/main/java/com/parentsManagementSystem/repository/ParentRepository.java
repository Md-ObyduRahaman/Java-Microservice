package com.parentsManagementSystem.repository;

import com.parentsManagementSystem.entity.ParentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<ParentDetails, Integer> {

}
