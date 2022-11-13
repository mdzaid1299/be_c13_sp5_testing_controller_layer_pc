package com.sprint.practice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Customer Already Exist")
public class CustomerAlreadyExistException extends Exception{
}
