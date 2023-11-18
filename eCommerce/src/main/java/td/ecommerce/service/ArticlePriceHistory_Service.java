package td.ecommerce.service;

import td.ecommerce.model.ArticlePriceHistory;
import java.util.List;

public interface ArticlePriceHistory_Service {

    public List<ArticlePriceHistory> getAllArticlesPriceHistory();
    public ArticlePriceHistory persistArticlesPriceHistory(ArticlePriceHistory articlePriceHistory);
    public ArticlePriceHistory getArticlePriceHistory(Long id);
    public ArticlePriceHistory updateArticlePriceHistory(ArticlePriceHistory articlePriceHistory);
    public void deleteArticlePriceHistory(Long id);
}

