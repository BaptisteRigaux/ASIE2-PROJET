package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import td.ecommerce.model.AdresseCustomers;
import td.ecommerce.repository.AdresseCustomers_Repository;
import td.ecommerce.service.AdresseCustomers_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class AdresseCustomersImpl implements AdresseCustomers_Service {
    private AdresseCustomers_Repository adresseCustomersRepository;

    @Autowired
    public AdresseCustomersImpl(AdresseCustomers_Repository adresseCustomersRepository) {
        this.adresseCustomersRepository = adresseCustomersRepository;
    }

    @Override
    public List<AdresseCustomers> getAllAddresses() {
        return adresseCustomersRepository.findAll();
    }

    @Override
    public AdresseCustomers persistAddress(AdresseCustomers address) {
        return adresseCustomersRepository.save(address);
    }

    @Override
    public AdresseCustomers getAddressById(Long id) {
        return adresseCustomersRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public AdresseCustomers updateAddress(AdresseCustomers address) {
        return adresseCustomersRepository.save(address);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(AdresseCustomersImpl.class);
    
    @Override
    public void deleteAddressById(Long id) {
        AdresseCustomers addressToDelete = adresseCustomersRepository.findById(id).orElse(null);
        if (addressToDelete != null) {
            
            LOGGER.info("Adresse found. Deleting...");
            adresseCustomersRepository.delete(addressToDelete);
            LOGGER.info("Adresse deleted successfully.");
            
        } else {
            LOGGER.warn("Adresse not found for deletion.");
            throw new EntityNotFoundException("Adresse not found for deletion.");
        }
    }
    
    @Override
    public List<AdresseCustomers> getAddressesByCustomersId(Long customerId) {
        
         return adresseCustomersRepository.findByCustomersCustomerId(customerId);
    }

    
}
