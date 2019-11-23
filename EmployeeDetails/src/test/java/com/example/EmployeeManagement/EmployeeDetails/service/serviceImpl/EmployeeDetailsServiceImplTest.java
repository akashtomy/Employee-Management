package com.example.EmployeeManagement.EmployeeDetails.service.serviceImpl;

import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsDTO;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsResponse;
import com.example.EmployeeManagement.EmployeeDetails.entity.EmployeeDetails;
import com.example.EmployeeManagement.EmployeeDetails.exception.InvalidEmployeeDetailsException;
import com.example.EmployeeManagement.EmployeeDetails.repository.EmployeeDetailsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

/**
 * The type Employee details service impl test.
 * @author Akash.Thomas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeDetailsServiceImplTest {
    /**
     * The Employee details repository.
     */
    @Mock
    private EmployeeDetailsRepository employeeDetailsRepository;

    /**
     * The Employee details service.
     */
    @InjectMocks
    private EmployeeDetailsServiceImpl employeeDetailsService;

    /**
     * Sets .
     */
    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
    }

    /**
     * Save employee details.
     */
    @Test
    public void saveEmployeeDetails() {
        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(false);
        expectedResult.setCode("EM_SAVE_SUCCESS");
        expectedResult.setMessage("Data is saved successfully");

        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setSalary(42000);
        employeeDetailsDTO.setEmployeeName("Test");
        employeeDetailsDTOList.add(employeeDetailsDTO);
        doReturn(employeeDetails).when(employeeDetailsRepository).saveAll(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.saveEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.OK), returnedResult);
    }

    /**
     * Save employee details for invalid details exception.
     */
    @Test(expected = InvalidEmployeeDetailsException.class)
    public void saveEmployeeDetailsForInvalidDetailsException() {
        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(false);
        expectedResult.setCode("EM_SAVE_SUCCESS");
        expectedResult.setMessage("Data is saved successfully");
        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setSalary(-400);
        employeeDetailsDTO.setEmployeeName("Test");
        employeeDetailsDTOList.add(employeeDetailsDTO);
        doReturn(employeeDetails).when(employeeDetailsRepository).saveAll(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.saveEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.OK), returnedResult);
    }

    /**
     * Update employee details for data not found.
     */
    @Test
    public void updateEmployeeDetailsForDataNotFound() {

        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(false);
        expectedResult.setCode("EM_UPDATE_FAILED");
        expectedResult.setMessage("No data Found");

        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        doReturn(employeeDetails).when(employeeDetailsRepository).findByEmployeeIdIn(anyList());
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setSalary(42000);
        employeeDetailsDTO.setEmployeeId(1);
        employeeDetailsDTO.setEmployeeName("Test");
        employeeDetailsDTOList.add(employeeDetailsDTO);
        doReturn(employeeDetails).when(employeeDetailsRepository).saveAll(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.updateEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.NOT_FOUND), returnedResult);
    }

    /**
     * Update employee details.
     */
    @Test
    public void updateEmployeeDetails() {

        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(false);
        expectedResult.setCode("EM_UPDATE_SUCCESS");
        expectedResult.setMessage("Data is updated successfully");

        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        EmployeeDetails employeeDetails1 = new EmployeeDetails();
        employeeDetails1.setEmployeeId(1);
        employeeDetails.add(employeeDetails1);
        doReturn(employeeDetails).when(employeeDetailsRepository).findByEmployeeIdIn(anyList());
        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setSalary(42000);
        employeeDetailsDTO.setEmployeeId(1);
        employeeDetailsDTO.setEmployeeName("Test");
        employeeDetailsDTOList.add(employeeDetailsDTO);
        doReturn(employeeDetails).when(employeeDetailsRepository).saveAll(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.updateEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.OK), returnedResult);
    }

    /**
     * Update employee details for exception.
     */
    @Test
    public void updateEmployeeDetailsForException() {

        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(true);
        expectedResult.setCode("EM_UPDATE_FAILED");
        expectedResult.setMessage("Error while fulfilling the request");

        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        doThrow(NullPointerException.class).when(employeeDetailsRepository).findByEmployeeIdIn(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.updateEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.INTERNAL_SERVER_ERROR), returnedResult);
    }


    /**
     * Max salary employee details.
     */
    @Test
    public void maxSalaryEmployeeDetails() {

        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(false);
        expectedResult.setCode("EM_FETCH_MAX_SALARY_EMPLOYEE_SUCCESS");
        expectedResult.setMessage("Data is updated successfully");

        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        EmployeeDetails employeeDetails1 = new EmployeeDetails();
        employeeDetails1.setEmployeeId(1);
        employeeDetails1.setSalary(1000);
        doReturn(employeeDetails).when(employeeDetailsRepository).findAll();
        employeeDetails.add(employeeDetails1);
        expectedResult.setData(employeeDetails1);
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.maxSalaryEmployeeDetails();
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.OK), returnedResult);
    }

    /**
     * Max salary employee details for no data found.
     */
    @Test
    public void maxSalaryEmployeeDetailsForNoDataFound() {

        EmployeeDetailsResponse expectedResult = new EmployeeDetailsResponse();
        expectedResult.setErrorStatus(false);
        expectedResult.setCode("EM_NO_DATA_FOUND");
        expectedResult.setMessage("No data Found");

        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        List<EmployeeDetails> employeeDetails = new ArrayList<>();
        doReturn(employeeDetails).when(employeeDetailsRepository).findAll();
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsService.maxSalaryEmployeeDetails();
        Assert.assertEquals(new ResponseEntity<>(expectedResult, HttpStatus.NOT_FOUND), returnedResult);
    }
}