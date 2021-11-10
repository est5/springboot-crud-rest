package com.msrs.mechanicservice.controller;

import com.msrs.mechanicservice.dto.CustomerDto;
import com.msrs.mechanicservice.model.Car;
import com.msrs.mechanicservice.model.ClosedOrder;
import com.msrs.mechanicservice.model.Customer;
import com.msrs.mechanicservice.model.OrderDetails;
import com.msrs.mechanicservice.repository.NonDbRepo;
import com.msrs.mechanicservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class OrderController {

    private final Logger logger = Logger.getLogger(OrderController.class.getName());
    private final OrderService orderService;
    private final NonDbRepo repo;

    public OrderController(OrderService orderService, NonDbRepo repo) {
        this.orderService = orderService;
        this.repo = repo;
    }
    //temp
    @GetMapping("/customers")
    public List<Customer> test(){
        return repo.getCustomerList();
    }
    //temp
    @GetMapping("/closed")
    public List<ClosedOrder> testClosed(){
        return repo.getClosedOrderList();
    }


    @GetMapping("/orders")
    public List<OrderDetails> getAllOrders() {
        return repo.getAllOrders();
    }

    @GetMapping("/ordersId")
    public OrderDetails getOrderById(
            @RequestParam long id
    ) {
        return repo.getOrderById(id);
    }

    @PostMapping("/createCustomer")
    public void createCustomer(
            @RequestBody CustomerDto customerDto
            ) {
        Customer c = new Customer();
        if (orderService.checkCustomer(c) == null) {
            c.setId(1);//autoincrement
            c.setPhoneNumber(customerDto.getPhoneNumber()); //forms
            c.setLastName(customerDto.getLastName());
            c.setFirstName(customerDto.getFirstName());
            repo.addCustomer(c);
        } else {
            logger.info("Customer with id " + c.getId() + " already in database");
        }
    }

    @PostMapping("/createCar")
    public void createCar(
            @RequestParam long customerId,
            @RequestBody Car car
    ) {
        if (orderService.checkCustomerHasCar(customerId, car)) {
            logger.info("Customer with id " + customerId + " already has car with id " + car.getId());
        } else {
            repo.findCustomerById(customerId).getCarList().add(car);
        }
    }

    @PostMapping("/createOrder")
    public void createOrder(
            @RequestParam long customerId,
            @RequestBody List<String> serviceList
    ) {
        Customer c = repo.findCustomerById(customerId);
        OrderDetails details = new OrderDetails();
        if (c == null) {
            logger.info("Create customer first");
        } else {
            if (c.getCarList().isEmpty()) {
                logger.info("Add car for customer with id: " + customerId);
            } else {
                //list of cars to select from on front
                details.setCar(c.getCarList().stream().filter(car -> car.getId() == 1).findFirst().orElse(null));
                details.setCustomer(repo.findCustomerById(customerId));
                details.setNotes("Init"); // enter notes from form
                details.setId(1); // auto-increment
                details.setPrice(new BigDecimal(200)); // calculate somehow
                details.setServiceList(serviceList); //checklist form
                orderService.createOrder(repo.findCustomerById(customerId), details);
            }
        }
    }

    @DeleteMapping("/closeOrder")
    public void closeOrder(
            @RequestParam long orderId
    ) {
        repo.closeOrder(orderId);
    }

    @PutMapping("/addNote")
    public void addNote(
            @RequestParam String note,
            @RequestParam long orderId
    ) {
        orderService.addNoteToOrder(orderId, note);
    }

}
