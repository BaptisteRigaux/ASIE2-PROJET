package td.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "adresse_customers")
public class AdresseCustomers {

    @Id
    @SequenceGenerator(
            name = "adresse_customers_sequence",
            sequenceName = "adresse_customers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adresse_customers_sequence")
    @Column(name = "adress_id")
    private Long adress_id;

    @Column(name = "street", nullable = true, columnDefinition = "TEXT")
    private String street;

    @Column(name = "city", nullable = true, columnDefinition = "TEXT")
    private String city;

    @Column(name = "postal_code", nullable = true)
    private int postal_code;

    @Column(name = "country", nullable = true, columnDefinition = "TEXT")
    private String country;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customers customers;

    public AdresseCustomers() {
    }

    public AdresseCustomers(String street, String city, int postal_code, String country, Customers customers) {
        this.street = street;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.customers = customers;
        customers.getAddresses().add(this);
    }

    public Long getAdress_id() {
        return adress_id;
    }

    public void setAdress_id(Long adress_id) {
        this.adress_id = adress_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(int postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresseCustomers that = (AdresseCustomers) o;
        return adress_id.equals(that.adress_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adress_id);
    }
}
