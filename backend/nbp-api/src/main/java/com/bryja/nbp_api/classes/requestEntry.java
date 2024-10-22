package com.bryja.nbp_api.classes;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class requestEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")Long id;

    @Column(nullable = false)
    private String currencyCode;
    @Column(nullable = false)
    private String requester;
    @Column(nullable = false)
    private LocalDateTime requestTime;
    @Column(nullable = false)
    private double value;




    public requestEntry() {}

    public requestEntry(String currencyCode, String requester, LocalDateTime requestTime,double value) {
        this.currencyCode = currencyCode;
        this.requester = requester;
        this.requestTime = requestTime;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }


}
