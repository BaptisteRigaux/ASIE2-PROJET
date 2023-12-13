package td.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import td.ecommerce.model.Article;

public interface Article_Repository extends JpaRepository<Article, Long>{

    void deleteById(Long id);

    @Query("SELECT o FROM Article o WHERE o.seller.seller_id = :sellerId")
    List<Article> findBySellerSellerId(@Param("sellerId")Long customerId); 

}
