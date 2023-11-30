package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import td.ecommerce.model.AdresseCustomers;
import td.ecommerce.repository.AdresseCustomers_Repository;
import td.ecommerce.service.AdresseCustomers_Service;

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
    public AdresseCustomers updateAddress(AdresseCustomers address) {
        return adresseCustomersRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        adresseCustomersRepository.deleteById(id);
    }
    
    @Override
    public List<AdresseCustomers> getAddressesByCustomersId(Long customerId) {
        
         return adresseCustomersRepository.findByCustomersCustomerId(customerId);
    }
}
