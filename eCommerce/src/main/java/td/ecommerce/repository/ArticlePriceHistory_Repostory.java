package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import td.ecommerce.model.ArticlePriceHistory;

public interface ArticlePriceHistory_Repostory  extends JpaRepository<ArticlePriceHistory, Long> {
}
