package com.example.EmployeeManagement.EmployeeDetails.controller;

import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsDTO;
import com.example.EmployeeManagement.EmployeeDetails.dto.EmployeeDetailsResponse;
import com.example.EmployeeManagement.EmployeeDetails.service.EmployeeDetailsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * The type Employee details controller test.
 * @author Akash.Thomas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class EmployeeDetailsControllerTest {

    /**
     * The Employee details controller.
     */
    @InjectMocks
    private EmployeeDetailsController employeeDetailsController;

    /**
     * The Employee details service.
     */
    @Mock
    private EmployeeDetailsService employeeDetailsService;

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
    void saveEmployeeDetails() {
        ResponseEntity<EmployeeDetailsResponse> expectedResult = mock(ResponseEntity.class);
        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        doReturn(expectedResult).when(employeeDetailsService).saveEmployeeDetails(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsController.saveEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(expectedResult, returnedResult);

    }

    /**
     * Update employee details.
     */
    @Test
    void updateEmployeeDetails() {
        ResponseEntity<EmployeeDetailsResponse> expectedResult = mock(ResponseEntity.class);
        List<EmployeeDetailsDTO> employeeDetailsDTOList = new ArrayList<>();
        doReturn(expectedResult).when(employeeDetailsService).updateEmployeeDetails(anyList());
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsController.updateEmployeeDetails(employeeDetailsDTOList);
        Assert.assertEquals(expectedResult, returnedResult);
    }

    /**
     * Max salary employee details.
     */
    @Test
    void maxSalaryEmployeeDetails() {
        ResponseEntity<EmployeeDetailsResponse> expectedResult = mock(ResponseEntity.class);
        doReturn(expectedResult).when(employeeDetailsService).maxSalaryEmployeeDetails();
        ResponseEntity<EmployeeDetailsResponse> returnedResult = employeeDetailsController.maxSalaryEmployeeDetails();
        Assert.assertEquals(expectedResult, returnedResult);
    }
}