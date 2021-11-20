package com.msrs.mechanicservice.model;


import com.msrs.mechanicservice.dto.OrderDto;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class OrderDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    private String notes;
    @ElementCollection
    private List<String> serviceList;
    private Date startDate;
    private BigDecimal price;
    private boolean isClosed;

    public OrderDetails() {
        this.startDate = Calendar.getInstance().getTime();
        serviceList = new ArrayList<>();
    }

    public static OrderDetails of(OrderDto orderDto, Customer customer) {
        var orderDetails = new OrderDetails();
        orderDetails.setPrice(new BigDecimal(14.88)); // calculate somehow
        orderDetails.setNotes(orderDto.getNote());
        orderDetails.setCustomer(customer);
        orderDetails.serviceList.add(orderDto.getList());
        orderDetails.setClosed(false);

        return orderDetails;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<String> serviceList) {
        this.serviceList = serviceList;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
