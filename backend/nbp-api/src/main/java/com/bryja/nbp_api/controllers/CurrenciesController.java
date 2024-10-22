package com.bryja.nbp_api.controllers;

import com.bryja.nbp_api.classes.dto.allEntriesResponseDTO;
import com.bryja.nbp_api.classes.dto.currentCurrencyValueRequestDTO;
import com.bryja.nbp_api.classes.dto.currentCurrencyValueResponseDTO;
import com.bryja.nbp_api.classes.requestEntry;
import com.bryja.nbp_api.exceptions.requestEntryInvalidException;
import com.bryja.nbp_api.services.CurrenciesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CurrenciesController {


    private final CurrenciesService currenciesService;

    public CurrenciesController(CurrenciesService currenciesService) {
        this.currenciesService = currenciesService;
    }


    @PostMapping(value ="/currencies/get-current-currency-value-command", consumes = {"*/*"})
    public ResponseEntity<?> getCurrentCurrencyValue(@RequestBody currentCurrencyValueRequestDTO request) {
            double value =  currenciesService.getCurrentCurrencyValue(request);
            currentCurrencyValueResponseDTO response = new currentCurrencyValueResponseDTO(value);
            return ResponseEntity.ok(response);
    }



    @GetMapping(value="/currencies/requests", consumes = {"*/*"})

    public List<allEntriesResponseDTO> getAllEntries() {
            return currenciesService.findAllEntries();
    }

}
