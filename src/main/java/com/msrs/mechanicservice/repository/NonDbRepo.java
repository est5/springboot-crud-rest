package com.msrs.mechanicservice.repository;

import org.springframework.stereotype.Repository;
import com.msrs.mechanicservice.model.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class NonDbRepo {
    private Logger logger = Logger.getLogger(NonDbRepo.class.getName());
    private List<OrderDetails> orderDetailsList;
    private List<Customer> customerList;
    private List<ClosedOrder> closedOrderList;

    public NonDbRepo(){
        this.orderDetailsList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.closedOrderList = new ArrayList<>();
    }
    public List<OrderDetails> getAllOrders(){
        return orderDetailsList;
    }

    private void addCustomer(Customer customer){
        if (customerList.contains(customer)){
            logger.info("Customer already created");
        }else{
            customerList.add(customer);
        }
    }

    public void createOrder(Customer customer,OrderDetails orderDetails){
        addCustomer(customer);
        orderDetailsList.add(orderDetails);
    }

    public OrderDetails findOrderById(long id){
        return orderDetailsList.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void closeOrder(long orderId) {
        OrderDetails details = findOrderById(orderId);
        ClosedOrder closedOrder = new ClosedOrder();
        closedOrder.setDetails(details);
        closedOrder.setCloseDate(Calendar.getInstance().getTime());
        closedOrderList.add(closedOrder);
        orderDetailsList.remove(details);
    }
}
