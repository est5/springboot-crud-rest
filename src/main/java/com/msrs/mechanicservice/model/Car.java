package com.msrs.mechanicservice.model;

public class Car {
    private long id;
    private Customer carOwner;
    private String manufacturer;
    private String model;
    private int year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(Customer carOwner) {
        this.carOwner = carOwner;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
