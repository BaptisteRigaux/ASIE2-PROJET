package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import td.ecommerce.model.ArticlePriceHistory;
import td.ecommerce.model.Panier;
import td.ecommerce.repository.ArticlePriceHistory_Repostory;
import td.ecommerce.repository.Panier_Repository;
import td.ecommerce.service.Panier_Service;

import java.util.List;

@Service
public class PanierServiceImpl implements Panier_Service {
    private final Panier_Repository panierRepository;
    private final ArticlePriceHistory_Repostory articlePriceHistoryRepository;

    @Autowired
    public PanierServiceImpl(Panier_Repository panierRepository, ArticlePriceHistory_Repostory articlePriceHistoryRepository) {
        this.panierRepository = panierRepository;
        this.articlePriceHistoryRepository = articlePriceHistoryRepository;
    }

    @Override
    public List<Panier> getAllPanier() {return panierRepository.findAll();}

    @Override
    public Panier createPanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public Panier getPanierById(Long id) {
        return panierRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addToPanier(Long panierId, Long articlePriceHistoryId) {
        Panier panier = panierRepository.findById(panierId).orElse(null);
        ArticlePriceHistory articlePriceHistory = articlePriceHistoryRepository.findById(articlePriceHistoryId).orElse(null);

        if (panier != null && articlePriceHistory != null) {
            panier.getArticlePriceHistory().add(articlePriceHistory);
            panierRepository.save(panier);
        }
    }

    @Override
    @Transactional
    public void removeFromPanier(Long panierId, Long articlePriceHistoryId) {
        Panier panier = panierRepository.findById(panierId).orElse(null);
        ArticlePriceHistory articlePriceHistory = articlePriceHistoryRepository.findById(articlePriceHistoryId).orElse(null);

        if (panier != null && articlePriceHistory != null) {
            panier.getArticlePriceHistory().remove(articlePriceHistory);
            panierRepository.save(panier);
        }
    }
}
