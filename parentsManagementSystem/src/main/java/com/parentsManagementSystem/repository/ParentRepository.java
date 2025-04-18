package com.parentsManagementSystem.repository;

import com.parentsManagementSystem.entity.ParentDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<ParentDetails, Integer> {

   Optional<ParentDetails> findByStudentId(Integer studentId);

   @Transactional
   @Modifying
   void deleteById(Integer studentId);


}
