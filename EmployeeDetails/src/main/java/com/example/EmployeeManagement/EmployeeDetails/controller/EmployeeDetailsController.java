package com.example.EmployeeManagement.EmployeeDetails.controller;

import com.example.EmployeeManagement.EmployeeDetails.constants.APIConstants;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsDTO;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsResponse;
import com.example.EmployeeManagement.EmployeeDetails.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Employee details controller.
 * @author Akash.Thomas
 */
@RestController
public class EmployeeDetailsController {

    /**
     * The Employee details service.
     */
    @Autowired
    private EmployeeDetailsService employeeDetailsService;


    /**
     * Save employee details response entity.
     *
     * @param employeeDetailsDTOS the employee details dtos
     * @return the response entity
     */
    @PostMapping(APIConstants.EMPLOYEE_MANAGEMENT_EMPLOYEE_DETAILS_SAVE_V_1)
    public ResponseEntity<EmployeeDetailsResponse> saveEmployeeDetails(
            @RequestBody List<EmployeeDetailsDTO> employeeDetailsDTOS) {
        return employeeDetailsService.saveEmployeeDetails(employeeDetailsDTOS);
    }

    @PutMapping(APIConstants.EMPLOYEE_MANAGEMENT_EMPLOYEE_DETAILS_UPDATE_V_1)
    public ResponseEntity<EmployeeDetailsResponse> updateEmployeeDetails(
            @RequestBody List<EmployeeDetailsDTO> employeeDetailsDTOS) {
        return employeeDetailsService.updateEmployeeDetails(employeeDetailsDTOS);
    }

    @GetMapping(APIConstants.EMPLOYEE_MANAGEMENT_EMPLOYEE_DETAILS_MAX_SALARY_V_1)
    public ResponseEntity<EmployeeDetailsResponse> maxSalaryEmployeeDetails() {
        return employeeDetailsService.maxSalaryEmployeeDetails();
    }
}
