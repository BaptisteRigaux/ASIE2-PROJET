package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import td.ecommerce.model.User;

public interface User_Repository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
