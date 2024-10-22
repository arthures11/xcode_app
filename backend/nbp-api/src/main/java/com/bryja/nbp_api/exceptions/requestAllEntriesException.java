package com.bryja.nbp_api.exceptions;

public class requestAllEntriesException extends RuntimeException{

    public requestAllEntriesException() {
        super("Try again later.");
    }
}
