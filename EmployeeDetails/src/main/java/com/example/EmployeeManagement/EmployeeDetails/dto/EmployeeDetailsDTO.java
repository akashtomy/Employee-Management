package com.example.EmployeeManagement.EmployeeDetails.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * The type Employee details dto.
 */
@Data
public class EmployeeDetailsDTO {

    /**
     * The Employee id.
     */
    @JsonProperty("employee_id")
    private Integer employeeId;

    /**
     * The Employee name.
     */
    @JsonProperty("employee_name")
    private String employeeName;

    /**
     * The Salary.
     */
    @JsonProperty("salary")
    private Integer salary;
}
