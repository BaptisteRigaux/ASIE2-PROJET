package td.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import td.ecommerce.model.Article;

public interface Article_Repository extends JpaRepository<Article, Long>{

    void deleteById(Long id);

}
