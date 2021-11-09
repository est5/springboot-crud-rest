package com.msrs.mechanicservice.service;

import com.msrs.mechanicservice.model.Customer;
import com.msrs.mechanicservice.model.OrderDetails;
import com.msrs.mechanicservice.repository.noDbTempRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private noDbTempRepo repo;

    public void createOrder(Customer customer, OrderDetails orderDetails){
        repo.createOrder(customer,orderDetails);
    }

    public void addNoteToOrder(long orderId, String note){
        OrderDetails details = repo.findOrderById(orderId);
        details.setNotes(details.getNotes() +"\n"+ note);
    }

    public void closeOrder(long orderId){
        repo.closeOrder(orderId);
    }

}
