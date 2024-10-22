package com.bryja.nbp_api.advices;

import com.bryja.nbp_api.exceptions.requestEntryInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class requestEntryInvalidAdvice {


    @ExceptionHandler(requestEntryInvalidException.class)
    public ResponseEntity<Map<String, String>> handleRequestEntryInvalidException(requestEntryInvalidException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("currencyCode", ex.getCurrencyCode());
        errorDetails.put("name", ex.getName());
        errorDetails.put("errorCode", "INVALID");

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
