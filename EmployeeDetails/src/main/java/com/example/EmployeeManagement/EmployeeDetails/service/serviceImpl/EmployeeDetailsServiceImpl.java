package com.example.EmployeeManagement.EmployeeDetails.service.serviceImpl;

import com.example.EmployeeManagement.EmployeeDetails.constants.ServiceConstants;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsDTO;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsResponse;
import com.example.EmployeeManagement.EmployeeDetails.entity.EmployeeDetails;
import com.example.EmployeeManagement.EmployeeDetails.exception.InvalidEmployeeDetailsException;
import com.example.EmployeeManagement.EmployeeDetails.repository.EmployeeDetailsRepository;
import com.example.EmployeeManagement.EmployeeDetails.service.EmployeeDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Employee details service.
 *
 * @author Akash.Thomas
 */

@Service
@Slf4j
public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {


    /**
     * The Employee details repository.
     */
    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    /**
     * Save employee details response entity.
     *
     * @param employeeDetailsDTOS the employee details dtos
     * @return the response entity
     */
    @Override
    public ResponseEntity<EmployeeDetailsResponse> saveEmployeeDetails(List<EmployeeDetailsDTO> employeeDetailsDTOS) {
        log.info("Enter in to saveEmployeeDetails");
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        try {
            employeeDetailsDTOS.parallelStream().forEachOrdered(employeeDetailsDTO -> {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                if (employeeDetailsDTO.getSalary() < 0) {
                    throw new InvalidEmployeeDetailsException(ServiceConstants.INVALID_EMPLOYEE_DETAILS);
                } else {
                    BeanUtils.copyProperties(employeeDetailsDTO, employeeDetails);
                    employeeDetailsList.add(employeeDetails);
                }
            });
            employeeDetailsRepository.saveAll(employeeDetailsList);
            employeeDetailsResponse.setErrorStatus(false);
            employeeDetailsResponse.setCode(ServiceConstants.EM_SAVE_SUCCESS);
            employeeDetailsResponse.setMessage(ServiceConstants.DATA_IS_SAVED_SUCCESSFULLY);
        } catch (InvalidEmployeeDetailsException invalidException) {
            log.error("Exit from saveEmployeeDetails with exception "+ invalidException.getLocalizedMessage());
            throw new InvalidEmployeeDetailsException(ServiceConstants.INVALID_EMPLOYEE_DETAILS);
        }
        log.info("Exit from saveEmployeeDetails");
        return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.OK);
    }

    /**
     * Update employee details response entity.
     *
     * @param employeeDetailsDTOS the employee details dtos
     * @return the response entity
     */
    @Override
    public ResponseEntity<EmployeeDetailsResponse> updateEmployeeDetails(List<EmployeeDetailsDTO> employeeDetailsDTOS) {

        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        try {
            List<Integer> employeeIds = employeeDetailsDTOS.parallelStream().filter(Objects::nonNull).map(EmployeeDetailsDTO::getEmployeeId).collect(Collectors.toList());
            List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findByEmployeeIdIn(employeeIds);
            if (employeeDetailsList.isEmpty()) {
                employeeDetailsResponse.setErrorStatus(false);
                employeeDetailsResponse.setCode(ServiceConstants.EM_UPDATE_FAILED);
                employeeDetailsResponse.setMessage(ServiceConstants.NO_DATA_FOUND);
                return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.NOT_FOUND);
            } else {
                for (EmployeeDetailsDTO employeeDetailsDTO : employeeDetailsDTOS) {
                    employeeDetailsList.stream().filter(employeeDetails -> employeeDetails.getEmployeeId().equals(employeeDetailsDTO.getEmployeeId()))
                            .forEach(employeeDetails -> employeeDetails.setSalary(employeeDetailsDTO.getSalary()));
                }
                employeeDetailsRepository.saveAll(employeeDetailsList);
                employeeDetailsResponse.setErrorStatus(false);
                employeeDetailsResponse.setCode(ServiceConstants.EM_UPDATE_SUCCESS);
                employeeDetailsResponse.setMessage(ServiceConstants.DATA_IS_UPDATED_SUCCESSFULLY);
            }
        } catch (Exception e) {
            e.printStackTrace();
            employeeDetailsResponse.setErrorStatus(true);
            employeeDetailsResponse.setCode(ServiceConstants.EM_UPDATE_FAILED);
            employeeDetailsResponse.setMessage(ServiceConstants.ERROR_WHILE_FULFILLING_THE_REQUEST);
            return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.OK);
    }

    /**
     * Max salary employee details response entity.
     *
     * @return the response entity
     */
    @Override
    public ResponseEntity<EmployeeDetailsResponse> maxSalaryEmployeeDetails() {
        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        try {
            List<EmployeeDetails> employeeDetailsList = employeeDetailsRepository.findAll();
            Optional<EmployeeDetails> employeeDetails = employeeDetailsList.stream().max(Comparator.comparing(EmployeeDetails::getSalary));
            if (employeeDetailsList.isEmpty()) {
                employeeDetailsResponse.setErrorStatus(false);
                employeeDetailsResponse.setCode(ServiceConstants.EM_NO_DATA_FOUND);
                employeeDetailsResponse.setMessage(ServiceConstants.NO_DATA_FOUND);
                return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.NOT_FOUND);
            } else {
                employeeDetailsResponse.setErrorStatus(false);
                employeeDetailsResponse.setCode(ServiceConstants.EM_FETCH_MAX_SALARY_EMPLOYEE_SUCCESS);
                employeeDetailsResponse.setMessage(ServiceConstants.DATA_IS_FETCHED_SUCCESSFULLY);
                employeeDetailsResponse.setData(employeeDetails.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
            employeeDetailsResponse.setErrorStatus(true);
            employeeDetailsResponse.setCode(ServiceConstants.EM_FETCH_MAX_SALARY_EMPLOYEE_FAILED);
            employeeDetailsResponse.setMessage(ServiceConstants.ERROR_WHILE_FULFILLING_THE_REQUEST);
            return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(employeeDetailsResponse, HttpStatus.OK);
    }
}
