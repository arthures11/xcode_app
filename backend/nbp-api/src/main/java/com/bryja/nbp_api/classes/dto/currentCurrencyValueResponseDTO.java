package com.bryja.nbp_api.classes.dto;

public class currentCurrencyValueResponseDTO {
    private double value;

    public currentCurrencyValueResponseDTO() {
    }

    public currentCurrencyValueResponseDTO(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
