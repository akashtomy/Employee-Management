package com.example.EmployeeManagement.EmployeeDetails.exception;

import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidEmployeeDetailsException.class)
    public ResponseEntity<EmployeeDetailsResponse> invalidEmployeeDetails(InvalidEmployeeDetailsException ex) {
        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        employeeDetailsResponse.setErrorStatus(true);
        employeeDetailsResponse.setCode(ex.getMessage());
        employeeDetailsResponse.setMessage("Invalid Employee Details");
        employeeDetailsResponse.setData(ex.getLocalizedMessage());
        return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.OK);
    }


}
