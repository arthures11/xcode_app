package com.bryja.nbp_api.classes.dto;

import java.time.LocalDateTime;

public class allEntriesResponseDTO {
    private String currency;
    private String name;
    private LocalDateTime date;
    private double value;

    public allEntriesResponseDTO() {
    }

    public allEntriesResponseDTO(String currency, String name, LocalDateTime date, double value) {
        this.currency = currency;
        this.name = name;
        this.date = date;
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
