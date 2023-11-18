package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import td.ecommerce.model.Order;

public interface Order_Repository extends JpaRepository<Order, Long>{

}
