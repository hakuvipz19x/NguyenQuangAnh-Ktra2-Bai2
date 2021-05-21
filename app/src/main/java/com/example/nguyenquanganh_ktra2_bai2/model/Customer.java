package com.example.nguyenquanganh_ktra2_bai2.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private int id;
    private String name;
    private String departure;
    private String date;
    private boolean luggage;

    public Customer() {
    }

    public Customer(String name, String departure, String date, boolean luggage) {
        this.name = name;
        this.departure = departure;
        this.date = date;
        this.luggage = luggage;
    }

    public Customer(int id, String name, String departure, String date, boolean luggage) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.date = date;
        this.luggage = luggage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isLuggage() {
        return luggage;
    }

    public void setLuggage(boolean luggage) {
        this.luggage = luggage;
    }
}
