package com.bryja.nbp_api.exceptions;

public class requestEntryInvalidException extends RuntimeException {

    private String currencyCode;
    private String name;
    public requestEntryInvalidException(String currencyCode, String name) {
        super("Invalid request or unsupported currency code.");
        this.currencyCode = currencyCode;
        this.name = name;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
    public String getName() {
        return name;
    }
}
