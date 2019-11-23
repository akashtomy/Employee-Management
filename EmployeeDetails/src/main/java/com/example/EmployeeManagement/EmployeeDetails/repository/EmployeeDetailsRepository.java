package com.example.EmployeeManagement.EmployeeDetails.repository;

import com.example.EmployeeManagement.EmployeeDetails.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Employee details repository.
 */
@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails,Integer> {

    List<EmployeeDetails> findByEmployeeIdIn(List<Integer> employeeIds);

}
