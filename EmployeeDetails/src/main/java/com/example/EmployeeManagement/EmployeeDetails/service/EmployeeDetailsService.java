package com.example.EmployeeManagement.EmployeeDetails.service;

import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsDTO;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The interface Employee details service.
 * @author Akash.Thomas
 */
public interface EmployeeDetailsService {

    /**
     * Save employee details response entity.
     *
     * @param employeeDetailsDTOS the employee details dtos
     * @return the response entity
     */
    ResponseEntity<EmployeeDetailsResponse> saveEmployeeDetails(List<EmployeeDetailsDTO> employeeDetailsDTOS);

    /**
     * Update employee details response entity.
     *
     * @param employeeDetailsDTOS the employee details dtos
     * @return the response entity
     */
    ResponseEntity<EmployeeDetailsResponse> updateEmployeeDetails(List<EmployeeDetailsDTO> employeeDetailsDTOS);

    /**
     * Max salary employee details response entity.
     *
     * @return the response entity
     */
    ResponseEntity<EmployeeDetailsResponse> maxSalaryEmployeeDetails();
}
