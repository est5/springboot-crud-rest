package com.msrs.mechanicservice.repository;

import com.msrs.mechanicservice.exception.OrderAlreadyCreatedException;
import com.msrs.mechanicservice.model.ClosedOrder;
import com.msrs.mechanicservice.model.Customer;
import com.msrs.mechanicservice.model.OrderDetails;
import org.springframework.stereotype.Repository;

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

    public void addCustomer(Customer customer){
        if (customerList.contains(customer)){
            return;
//            logger.info("Customer already created");
        }else{
            customerList.add(customer);
        }
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void createOrder(Customer customer, OrderDetails orderDetails){
        addCustomer(customer);
        if (orderDetailsList.stream().filter(details -> details.getId() == orderDetails.getId()).findFirst().orElse(null) == null){
            orderDetailsList.add(orderDetails);
        }else{
            throw new OrderAlreadyCreatedException();
        }

    }

    public OrderDetails getOrderById(long id){
        return orderDetailsList.stream()
                .filter(o -> o.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void closeOrder(long orderId) {
        OrderDetails details = getOrderById(orderId);
        ClosedOrder closedOrder = new ClosedOrder();
        closedOrder.setDetails(details);
        closedOrder.setCloseDate(Calendar.getInstance().getTime());
        closedOrderList.add(closedOrder);
        orderDetailsList.remove(details);
    }

    public Customer findCustomerById(long id){
        return customerList.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<ClosedOrder> getClosedOrderList() {
        return closedOrderList;
    }
}
