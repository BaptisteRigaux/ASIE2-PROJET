package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import td.ecommerce.model.Article;
import td.ecommerce.repository.Article_Repository;
import td.ecommerce.service.Article_Service;

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
}
