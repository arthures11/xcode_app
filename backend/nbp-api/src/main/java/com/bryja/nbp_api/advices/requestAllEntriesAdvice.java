package com.bryja.nbp_api.advices;

import com.bryja.nbp_api.exceptions.requestAllEntriesException;
import com.bryja.nbp_api.exceptions.requestEntryInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class requestAllEntriesAdvice {


    @ExceptionHandler(requestAllEntriesException.class)
    public ResponseEntity<Map<String, String>> handleRequestAllEntriesException(requestAllEntriesException ex) {
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("errorCode", "ERROR");

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
