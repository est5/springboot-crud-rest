package com.msrs.mechanicservice.repository.postgresql;

import com.msrs.mechanicservice.model.OrderDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderDetailsRepo extends CrudRepository<OrderDetails, Long> {
    @Query("SELECT d FROM OrderDetails d WHERE d.isClosed = :closed ")
    Iterable<OrderDetails> findByClosed(@Param(value = "closed") Boolean bool);
}
