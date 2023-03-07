package com.kodlamaio.bootcampproject;

import com.kodlamaio.bootcampproject.core.utilities.exceptions.BusinessException;
import com.kodlamaio.bootcampproject.core.utilities.results.ErrorDataResult;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class BootcampProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootcampProjectApplication.class, args);
    }

}
