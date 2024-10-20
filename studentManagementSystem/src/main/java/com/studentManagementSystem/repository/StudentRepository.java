package com.ParentDetailsManagementSystem.repository;

import com.ParentDetailsManagementSystem.entity.ParentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface parentRepository extends JpaRepository<ParentDetails, Integer> {

}
