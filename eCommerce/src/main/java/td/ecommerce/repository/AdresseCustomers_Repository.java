package td.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import td.ecommerce.model.AdresseCustomers;


public interface AdresseCustomers_Repository extends JpaRepository<AdresseCustomers, Long>{

    @Query("SELECT o FROM AdresseCustomers o WHERE o.customers.customer_id = :customerId")
    List<AdresseCustomers> findByCustomersCustomerId(@Param("customerId")Long customerId); 
}
