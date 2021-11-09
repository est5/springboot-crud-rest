package com.msrs.mechanicservice.controller;

import com.msrs.mechanicservice.model.Car;
import com.msrs.mechanicservice.model.Customer;
import com.msrs.mechanicservice.model.OrderDetails;
import com.msrs.mechanicservice.repository.NonDbRepo;
import com.msrs.mechanicservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;
    private NonDbRepo repo;

    public OrderController(OrderService orderService, NonDbRepo repo) {
        this.orderService = orderService;
        this.repo = repo;
    }

    @GetMapping("/orders")
    public List<OrderDetails> getAllOrders() {
        return repo.getAllOrders();
    }

    @GetMapping("/ordersId")
    public OrderDetails getOrderById(
            @RequestParam long id
    ) {
        return repo.findOrderById(id);
    }

    @PostMapping("/add")
    public void addOrder(
            @RequestBody OrderDetails details
    ) {
        Customer testCustomer = new Customer();
        testCustomer.setId(1);
        testCustomer.setFirstName("Name");
        testCustomer.setLastName("LastName");
        testCustomer.setPhoneNumber("123456");
        Car testCar = new Car();
        testCar.setId(1);
        testCar.setManufacturer("bimer");
        testCar.setModel("x5");
        testCar.setYear(2022);
        testCustomer.getCarList().add(testCar);
        details.setCustomer(testCustomer);
        details.setCar(testCar);
        repo.createOrder(testCustomer, details);
    }
}
