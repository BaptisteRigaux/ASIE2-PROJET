package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import td.ecommerce.model.Article;
import td.ecommerce.repository.Article_Repository;
import td.ecommerce.service.Article_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ArticleServiceImpl implements Article_Service {
    private Article_Repository articleRepository;

    @Autowired
    public ArticleServiceImpl(Article_Repository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article persistArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> getArticlesBySeller(Long sellerId) {
        
         return articleRepository.findBySellerSellerId(sellerId);
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);
    
    @Override
    public void deleteArticleById(Long id) {
        Article articleToDelete = articleRepository.findById(id).orElse(null);
        if (articleToDelete != null) {
            
            LOGGER.info("Adresse found. Deleting...");
            articleRepository.delete(articleToDelete);
            LOGGER.info("Adresse deleted successfully.");
            
        } else {
            LOGGER.warn("Adresse not found for deletion.");
            throw new EntityNotFoundException("Adresse not found for deletion.");
        }
    }
}
