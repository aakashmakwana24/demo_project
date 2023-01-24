package com.Department.Exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//https://www.bezkoder.com/spring-boot-restcontrolleradvice/
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message);
		return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> RMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
	{
		Map<String,String> errormap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String errorname = ((FieldError)error).getField();
			String errormess = error.getDefaultMessage();
			errormap.put(errorname, errormess);
		});
		return new ResponseEntity<>(errormap,HttpStatus.BAD_REQUEST);
	}
	
}
