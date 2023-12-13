package td.ecommerce.service;
import td.ecommerce.model.Panier;
import java.util.List;

public interface Panier_Service {
    Panier createPanier(Panier panier);

    public List<Panier> getAllPanier();

    Panier getPanierById(Long id);

    void addToPanier(Long panierId, Long articleId);

    void removeFromPanier(Long panierId, Long articleId);
    
    void clearUserPanier(Long userId);
}
