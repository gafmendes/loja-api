package com.mendes.java.back.end.user_api.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mendes.java.back.end.shopping_client.dto.ErrorDTO;
import com.mendes.java.back.end.shopping_client.exception.UserNotFoundException;

@ControllerAdvice(basePackages = "com.mendes.java.back.end.controller")
public class UserControllerAdvice {
 
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handleUserNotFound(UserNotFoundException userNotFoundException) {    	
    	ErrorDTO errorDTO = new ErrorDTO();
    	errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
    	errorDTO.setMessage("Usuário não encontrado.");
    	errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }
}