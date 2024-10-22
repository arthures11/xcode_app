package com.bryja.nbp_api;

import com.bryja.nbp_api.classes.dto.allEntriesResponseDTO;
import com.bryja.nbp_api.classes.dto.currentCurrencyValueRequestDTO;
import com.bryja.nbp_api.classes.dto.currentCurrencyValueResponseDTO;
import com.bryja.nbp_api.classes.requestEntry;
import com.bryja.nbp_api.exceptions.requestEntryInvalidException;
import com.bryja.nbp_api.services.CurrenciesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CurrenciesControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CurrenciesService currencyService;


    @Autowired
    private ObjectMapper objectMapper;




//    @Test
//    public void testGetCurrencyValueSuccessUseHttpClient() throws Exception {
//        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("EUR", "Jan Nowak");
//
//        this.mockMvc.perform(post("/currencies/get-current-currency-value-command")
//                        .contentType(MediaType.APPLICATION_JSON)
//                    //    .content("{\"currency\": \"EUR\", \"name\": \"Artur Bryja\"}"))
//                .content(objectMapper.writeValueAsString(request)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.value", isA(Double.class)))
//                .andExpect(jsonPath("$.value", greaterThanOrEqualTo(0.0)));
//
//
//    }

    @Test
    public void testGetCurrencyValueSuccess() throws Exception {
        double expectedValue = 4.2954;
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("EUR", "Artur Bryja");
        currentCurrencyValueResponseDTO response = new currentCurrencyValueResponseDTO(expectedValue);

        when(currencyService.getCurrentCurrencyValue(any(currentCurrencyValueRequestDTO.class))).thenReturn(response.getValue());

        this.mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", isA(Double.class)))
                .andExpect(jsonPath("$.value", greaterThanOrEqualTo(0.0)))
                .andExpect(jsonPath("$.value").value(4.2954));
    }

    @Test
    public void testGetCurrencyValueMissingName() throws Exception {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("EUR", null);
        when(currencyService.getCurrentCurrencyValue(any(currentCurrencyValueRequestDTO.class))).thenThrow(new requestEntryInvalidException(request.getCurrency(), request.getName()));

        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.currencyCode").value("EUR"))
                .andExpect(jsonPath("$.errorCode").value("INVALID"));
    }



    @Test
    public void testGetCurrencyValueMissingCurrency() throws Exception {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO(null, "Jan Nowak");
        when(currencyService.getCurrentCurrencyValue(any(currentCurrencyValueRequestDTO.class))).thenThrow(new requestEntryInvalidException(request.getCurrency(), request.getName()));
        mockMvc.perform(post("/currencies/get-current-currency-value-command")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.name").value("Jan Nowak"))
                .andExpect(jsonPath("$.errorCode").value("INVALID"));
    }
    @Test
    public void testGetRequests() throws Exception {
        allEntriesResponseDTO entity = new allEntriesResponseDTO("EUR","Artur Bryja", LocalDateTime.now(), 4.291);
        when(currencyService.findAllEntries()).thenReturn(Collections.singletonList(entity));


        mockMvc.perform(get("/currencies/requests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].currency").value("EUR"))
                .andExpect(jsonPath("$[0].name").value("Artur Bryja"))
                .andExpect(jsonPath("$[0].date").exists())
                .andExpect(jsonPath("$[0].value").value(4.291));
    }



}
