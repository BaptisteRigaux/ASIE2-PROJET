package td.ecommerce.service;

import td.ecommerce.model.Article;
import td.ecommerce.model.ArticlePriceHistory;

import java.util.List;

public interface Article_Service {
    public List<Article> getAllArticles();

    public Article persistArticle(Article article);

    public Article getArticleById(Long id);

    public Article updateArticle(Article article);

    public void deleteArticle(Long id);

    public void deleteArticleById(Long id);

    public List<Article> getArticlesBySeller(Long sellerId);

    public ArticlePriceHistory getLatestPriceHistoryForArticle(Long articleId);
}
