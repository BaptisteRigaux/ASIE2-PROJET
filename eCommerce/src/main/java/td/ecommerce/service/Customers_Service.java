package td.ecommerce.service;

import td.ecommerce.model.Customers;
import java.util.List;

public interface Customers_Service {
    public List<Customers> getAllCustomers();

    public Customers persistCustomers(Customers customers);
    public Customers getCustomersById(Long id);

    public Customers updateCustomers(Customers customers);

    public void deleteCustomers(Long id);
}
