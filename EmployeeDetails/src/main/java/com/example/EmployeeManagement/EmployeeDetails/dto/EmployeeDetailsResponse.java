package com.example.EmployeeManagement.EmployeeDetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * The type Employee details response.
 * @author Akash.Thomas
 */
@Data
public class EmployeeDetailsResponse {
    /**
     * The error status.
     */
    @JsonProperty("error_status")
    private Boolean errorStatus = true;

    /**
     * The code.
     */
    @JsonProperty("code")
    private String code;

    /**
     * The message.
     */
    @JsonProperty("message")
    private String message;

    /**
     * The data list.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("data")
    private Object data;
}


