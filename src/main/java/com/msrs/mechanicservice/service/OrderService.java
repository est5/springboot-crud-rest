package com.msrs.mechanicservice.service;

import com.msrs.mechanicservice.exception.CustomerNotExistsException;
import com.msrs.mechanicservice.model.Car;
import com.msrs.mechanicservice.model.Customer;
import com.msrs.mechanicservice.model.OrderDetails;
import com.msrs.mechanicservice.repository.NonDbRepo;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private NonDbRepo repo;

    public OrderService(NonDbRepo repo) {
        this.repo = repo;
    }

    public void createOrder(Customer customer, OrderDetails orderDetails) {
        repo.createOrder(customer, orderDetails);
    }

    public void addNoteToOrder(long orderId, String note) {
        OrderDetails details = repo.getOrderById(orderId);
        details.setNotes(details.getNotes() + ". " + note);
    }

    public void closeOrder(long orderId) {
        repo.closeOrder(orderId);
    }

    public Customer checkCustomer(Customer customer) {
        return repo.getCustomerList()
                .stream()
                .filter(c -> c.getId() == customer.getId())
                .findFirst()
                .orElse(null);
    }

    public boolean checkCustomerHasCar(long id, Car car) {
        Customer c = repo.findCustomerById(id);
        if (c == null){
            throw new CustomerNotExistsException();
        }else{
            return c.getCarList().contains(car);
        }
    }
}
