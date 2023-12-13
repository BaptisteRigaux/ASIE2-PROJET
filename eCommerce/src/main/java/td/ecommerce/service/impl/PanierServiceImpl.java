package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import td.ecommerce.model.Article;
import td.ecommerce.model.Panier;
import td.ecommerce.repository.Article_Repository;
import td.ecommerce.repository.Panier_Repository;
import td.ecommerce.service.Panier_Service;

import java.util.List;

@Service
public class PanierServiceImpl implements Panier_Service {
    private final Panier_Repository panierRepository;
    private final Article_Repository articleRepository;

    @Autowired
    public PanierServiceImpl(Panier_Repository panierRepository,Article_Repository articleRepository) {
        this.panierRepository = panierRepository;
        this.articleRepository = articleRepository;
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
    public void addToPanier(Long panierId, Long articleId) {
        Panier panier = panierRepository.findById(panierId).orElse(null);
        Article articleToPanier = articleRepository.findById(articleId).orElse(null);

        if (panier != null && articleToPanier != null) {
            panier.getArticles().add(articleToPanier);
            panierRepository.save(panier);
        }
    }

    @Override
    @Transactional
    public void removeFromPanier(Long panierId, Long articleId) {
        Panier panier = panierRepository.findById(panierId).orElse(null);
        Article articleToPanier = articleRepository.findById(articleId).orElse(null);

        if (panier != null && articleToPanier != null) {
            panier.getArticles().remove(articleToPanier);
            panierRepository.save(panier);
        }
    }

    @Override
    public void clearUserPanier(Long userId) {
        panierRepository.deleteByUserId(userId);
    }
}
