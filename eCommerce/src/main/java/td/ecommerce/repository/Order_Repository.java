package td.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import td.ecommerce.model.Order;

@Repository
public interface Order_Repository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.customers.customer_id = :customerId")
    List<Order> findByCustomersCustomerId(@Param("customerId")Long customerId);    


    @Query("SELECT MAX(o.number_order) FROM Order o")
    Integer findMaxOrderNumber();
}
