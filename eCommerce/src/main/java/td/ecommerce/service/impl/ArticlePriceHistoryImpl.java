package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import td.ecommerce.model.ArticlePriceHistory;
import td.ecommerce.repository.ArticlePriceHistory_Repostory;
import td.ecommerce.service.ArticlePriceHistory_Service;

import java.util.List;

@Service
public class ArticlePriceHistoryImpl implements ArticlePriceHistory_Service {
    private ArticlePriceHistory_Repostory articlePriceHistoryRepostory;

    @Autowired
    public ArticlePriceHistoryImpl(ArticlePriceHistory_Repostory articlePriceHistoryRepostory) {
        this.articlePriceHistoryRepostory = articlePriceHistoryRepostory;
    }

    @Override
    public List<ArticlePriceHistory> getAllArticlesPriceHistory() {
        return articlePriceHistoryRepostory.findAll();
    }

    @Override
    public ArticlePriceHistory persistArticlesPriceHistory(ArticlePriceHistory articlePriceHistory) {
        return articlePriceHistoryRepostory.save(articlePriceHistory);
    }

    @Override
    public ArticlePriceHistory getArticlePriceHistory(Long id) {
        return articlePriceHistoryRepostory.findById(id).orElse(null);
    }

    @Override
    public ArticlePriceHistory updateArticlePriceHistory(ArticlePriceHistory articlePriceHistory) {
        return articlePriceHistoryRepostory.save(articlePriceHistory);
    }

    @Override
    public void deleteArticlePriceHistory(Long id) {
        articlePriceHistoryRepostory.deleteById(id);
    }
}
