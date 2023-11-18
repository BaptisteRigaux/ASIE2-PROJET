package td.ecommerce.service;

import td.ecommerce.model.Seller;

import java.util.List;

public interface Seller_Service {

    public List<Seller> getAllSeller();
    public Seller createSeller(Seller seller);

    public Seller getSellerById(Long id);

    public Seller updateSeller(Seller seller);

    public void deleteSeller(Long id);
}
