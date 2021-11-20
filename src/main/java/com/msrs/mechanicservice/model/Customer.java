package com.msrs.mechanicservice.model;

import com.msrs.mechanicservice.dto.CustomerDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String carManufacturer;
    private String carModel;
    private int carYear;

    public static Customer of(CustomerDto customerDto) {
        Customer c = new Customer();
        c.setFirstName(customerDto.getFirstName());
        c.setLastName(customerDto.getLastName());
        c.setPhoneNumber(customerDto.getPhoneNumber());
        c.setCarManufacturer(customerDto.getCarManufacturer());
        c.setCarModel(customerDto.getCarModel());
        c.setCarYear(customerDto.getCarYear());

        return c;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
