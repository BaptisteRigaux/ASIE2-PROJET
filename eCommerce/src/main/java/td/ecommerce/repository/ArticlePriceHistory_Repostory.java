package td.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import td.ecommerce.model.ArticlePriceHistory;

public interface ArticlePriceHistory_Repostory  extends JpaRepository<ArticlePriceHistory, Long> {

    @Query(value = "SELECT * FROM article_price_history WHERE article_id = :articleId ORDER BY date_end DESC LIMIT 1", nativeQuery = true)
    Optional<ArticlePriceHistory> findLatestByArticleId(@Param("articleId") Long articleId);

}
