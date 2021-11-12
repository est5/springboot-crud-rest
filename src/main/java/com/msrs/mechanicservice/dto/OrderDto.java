package com.msrs.mechanicservice.dto;

public class OrderDto {

    private Long customerId;
    private String note;
    private String list;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
