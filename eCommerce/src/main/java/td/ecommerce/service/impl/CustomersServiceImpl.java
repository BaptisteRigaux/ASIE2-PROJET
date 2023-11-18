package td.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import td.ecommerce.model.Customers;
import td.ecommerce.repository.Customers_Repository;
import td.ecommerce.service.Customers_Service;

import java.util.List;

@Service
public class CustomersServiceImpl implements Customers_Service {
    private Customers_Repository customersRepository;
    @Autowired
    public CustomersServiceImpl(Customers_Repository customersRepository){
        this.customersRepository = customersRepository;
    }

    @Override
    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    @Override
    public Customers persistCustomers(Customers customers) {
        return customersRepository.save(customers);
    }

    @Override
    public Customers getCustomersById(Long id) {
        return customersRepository.findById(id).orElseThrow();
    }

    @Override
    public Customers updateCustomers(Customers customers) {
        return customersRepository.save(customers);
    }

    @Override
    public void deleteCustomers(Long id) {
        customersRepository.findById(id).orElseThrow();
        customersRepository.deleteById(id);
    }
}
