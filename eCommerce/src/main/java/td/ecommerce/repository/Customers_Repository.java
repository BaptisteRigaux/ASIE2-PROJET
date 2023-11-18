package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import td.ecommerce.model.Customers;

public interface Customers_Repository extends JpaRepository<Customers, Long>{

}
