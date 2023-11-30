package td.ecommerce.service;

import td.ecommerce.model.AdresseCustomers;
import java.util.List;

public interface AdresseCustomers_Service {
    public List<AdresseCustomers> getAllAddresses();
    public AdresseCustomers persistAddress(AdresseCustomers address);
    public AdresseCustomers getAddressById(Long id);
    public AdresseCustomers updateAddress(AdresseCustomers address);
    public void deleteAddress(Long id);

    public List<AdresseCustomers>getAddressesByCustomersId(Long customerId);
}
