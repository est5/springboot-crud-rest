package com.msrs.mechanicservice.repository.postgresql;

import com.msrs.mechanicservice.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    Customer getCustomerByFirstNameAndLastName(String firstName, String lastName);

}
