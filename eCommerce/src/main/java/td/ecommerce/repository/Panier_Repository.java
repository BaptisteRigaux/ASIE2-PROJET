package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import td.ecommerce.model.Panier;

public interface Panier_Repository extends JpaRepository<Panier, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Panier p WHERE p.user.id = :userId")
    void deleteByUserId(@Param("userId") Long userId);
}
