package com.bryja.nbp_api;

import com.bryja.nbp_api.classes.dto.currentCurrencyValueRequestDTO;
import com.bryja.nbp_api.exceptions.requestEntryInvalidException;
import com.bryja.nbp_api.repository.requestEntryRepository;
import com.bryja.nbp_api.services.CurrenciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CurrenciesServiceTest {


    @Mock
    private requestEntryRepository requestEntryRepository;

    @InjectMocks
    private CurrenciesService currenciesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldThrowExceptionWhenCodeIsNull() {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO(null, "Artur Bryja");

        assertThrows(requestEntryInvalidException.class,
                () -> currenciesService.getCurrentCurrencyValue(request));

        verify(requestEntryRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenCodeIsEmpty() {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("", "Artur Bryja");

        assertThrows(requestEntryInvalidException.class,
                () -> currenciesService.getCurrentCurrencyValue(request));

        verify(requestEntryRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("EUR", "");

        assertThrows(requestEntryInvalidException.class,
                () -> currenciesService.getCurrentCurrencyValue(request));

        verify(requestEntryRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("EUR", null);

        assertThrows(requestEntryInvalidException.class,
                () -> currenciesService.getCurrentCurrencyValue(request));

        verify(requestEntryRepository, never()).save(any());
    }

    @Test
    void shouldReturnValueWhenBothFieldsAreValid() throws Exception {
        currentCurrencyValueRequestDTO request = new currentCurrencyValueRequestDTO("EUR", "Artur Bryja");

        assertDoesNotThrow(() -> currenciesService.getCurrentCurrencyValue(request));
    }

}