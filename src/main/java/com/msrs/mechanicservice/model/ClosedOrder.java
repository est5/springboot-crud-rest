package com.msrs.mechanicservice.model;

import java.util.Date;

public class ClosedOrder {
    private Date closeDate;
    private OrderDetails details;

    public OrderDetails getDetails() {
        return details;
    }

    public void setDetails(OrderDetails orderDetails) {
        details = orderDetails;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
