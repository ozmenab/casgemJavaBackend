package com.kodlamaio.bootcampproject.core.utilities.exceptions;

import com.kodlamaio.bootcampproject.core.utilities.results.ErrorDataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleBusinessException(BusinessException businessException){
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<>(businessException.getMessage(),"BUSİNESS.EXCEPTION");
        log.error(businessException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDataResult);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> validationErrors = new HashMap<String,String>();
        for(FieldError fieldError: methodArgumentNotValidException.getBindingResult().getFieldErrors()){
            validationErrors.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<>(validationErrors,"VALIDATION.EXCEPTION");
        log.warn(errorDataResult.getData().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDataResult);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException missingPathVariableException,
                                                               HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<>(missingPathVariableException.getMessage(), "MISSING PATH VARIABLE.EXCEPTION");
        log.warn(errorDataResult.getData().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDataResult);
    }
}
