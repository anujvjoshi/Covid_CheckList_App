package com.covidchecklist.app.dto;

import org.springframework.http.HttpStatus;

import com.covidchecklist.app.exception.ErrorDetails;

import lombok.Data;

@Data
public class Response {

	public static String SUCCESS_STATUS = "SUCCESS";

	public static String FAIL_STATUS = "FAIL";

	private String status;

	private Object responseData;

	private ErrorDetails errorDetails;

	private HttpStatus httpStatus;

}
