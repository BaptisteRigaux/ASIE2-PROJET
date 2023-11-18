package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import td.ecommerce.model.Panier;

public interface Panier_Repository extends JpaRepository<Panier, Long> {
}
