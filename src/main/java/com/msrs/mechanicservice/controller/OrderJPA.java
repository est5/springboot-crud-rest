package com.msrs.mechanicservice.controller;

import com.msrs.mechanicservice.dto.CustomerDto;
import com.msrs.mechanicservice.dto.OrderDto;
import com.msrs.mechanicservice.model.Customer;
import com.msrs.mechanicservice.model.OrderDetails;
import com.msrs.mechanicservice.repository.postgresql.CustomerRepo;
import com.msrs.mechanicservice.repository.postgresql.OrderDetailsRepo;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderJPA {

    private final CustomerRepo customerRepo;
    private final OrderDetailsRepo orderDetailsRepo;

    public OrderJPA(CustomerRepo customerRepo, OrderDetailsRepo orderDetailsRepo) {
        this.customerRepo = customerRepo;
        this.orderDetailsRepo = orderDetailsRepo;
    }

    @PostMapping("/customer/new")
    public void createCustomer(
            @RequestBody CustomerDto customerDto
    ) {
        customerRepo.save(Customer.of(customerDto));
    }

    @PostMapping("/order/new")
    public void createOrder(
            @RequestBody OrderDto orderDto
    ) {
        var customer = customerRepo.findById(orderDto.getCustomerId());

        orderDetailsRepo.save(OrderDetails.of(orderDto, customer.get()));
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(
            @PathVariable Long id
    ) {
        return customerRepo.findById(id).get();
    }

    @GetMapping("/order/{id}")
    public OrderDetails getOrder(
            @PathVariable Long id
    ) {
        return orderDetailsRepo.findById(id).get();
    }

    @PutMapping("/note/{orderId}")
    public void addNote(
            @PathVariable Long orderId,
            @RequestBody String notes
    ) {
        var o = orderDetailsRepo.findById(orderId).get();
        o.setNotes(o.getNotes() + " " + notes);
        orderDetailsRepo.save(o);
    }

    @PutMapping("/order/close/{id}")
    public void closeOrder(
            @PathVariable Long id
    ) {
        var order = orderDetailsRepo.findById(id).get();
        order.setClosed(true);
        orderDetailsRepo.save(order);
    }

    @GetMapping("/order/closed")
    public Iterable<OrderDetails> getClosedOrders() {
        return (orderDetailsRepo.findByClosed(true));
    }
}
