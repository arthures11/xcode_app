package com.bryja.nbp_api.services;

import com.bryja.nbp_api.classes.dto.allEntriesResponseDTO;
import com.bryja.nbp_api.classes.dto.currentCurrencyValueRequestDTO;
import com.bryja.nbp_api.classes.requestEntry;
import com.bryja.nbp_api.exceptions.requestAllEntriesException;
import com.bryja.nbp_api.exceptions.requestEntryInvalidException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrenciesService {

    private final com.bryja.nbp_api.repository.requestEntryRepository requestEntryRepository;

    public CurrenciesService(com.bryja.nbp_api.repository.requestEntryRepository requestEntryRepository) {
        this.requestEntryRepository = requestEntryRepository;
    }
    public double getCurrentCurrencyValue(currentCurrencyValueRequestDTO request) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            if(request.getName()==null || request.getCurrency()==null){
                throw new requestEntryInvalidException(request.getCurrency(), request.getName());
            }
            else if(request.getName().isEmpty() || request.getCurrency().isEmpty()){
                throw new requestEntryInvalidException(request.getCurrency(), request.getName());
            }
            String apiUrl = "https://api.nbp.pl/api/exchangerates/rates/A/" + request.getCurrency() + "?format=json";

            ResponseEntity<String> nbpResponse = restTemplate.getForEntity(apiUrl, String.class);

            if (nbpResponse.getStatusCode() == HttpStatus.OK) {
                String responseBody = nbpResponse.getBody();
                double midValue = extractMidValueFromResponse(responseBody);
                String name = extractCurrencyNameFromResponse(responseBody);
                requestEntry entry = new requestEntry(name,request.getName(), LocalDateTime.now(),midValue);
                requestEntryRepository.save(entry);
                return midValue;

            } else {
                throw new requestEntryInvalidException(request.getCurrency(), request.getName());
            }

        } catch (Exception e) {
            throw new requestEntryInvalidException(request.getCurrency(), request.getName());
        }
    }

    public List<allEntriesResponseDTO> findAllEntries() {
        try {
            return requestEntryRepository.findAll()
                    .stream()
                    .map(entry -> new allEntriesResponseDTO(
                            entry.getCurrencyCode(),
                            entry.getRequester(),
                            entry.getRequestTime(),
                            entry.getValue()
                    ))
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            throw new requestAllEntriesException();
        }
    }


    private double extractMidValueFromResponse(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode ratesNode = rootNode.path("rates").get(0);
        return ratesNode.path("mid").asDouble();
    }
    private String extractCurrencyNameFromResponse(String responseBody) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = mapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        JsonNode ratesNode = rootNode.path("code");
        return ratesNode.asText();
    }

}
