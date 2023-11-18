package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import td.ecommerce.model.Seller;
import td.ecommerce.repository.Seller_Repository;
import td.ecommerce.service.Seller_Service;

import java.util.List;

@Service
public class SellerServiceImpl implements Seller_Service {
    private final Seller_Repository sellerRepository;


    @Autowired
    public SellerServiceImpl(Seller_Repository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<Seller> getAllSeller() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Seller updateSeller(Seller seller) {
        if (sellerRepository.existsById(seller.getSeller_id())) {
            return sellerRepository.save(seller);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }
}
