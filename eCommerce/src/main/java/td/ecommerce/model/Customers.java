package td.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @SequenceGenerator(
            name = "customers_sequence",
            sequenceName = "customers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_sequence")
    @Column(name = "customer_id")
    private Long customer_id;

    @Column(name = "command_site")
    private Boolean command_site;

    @Column(name = "command")
    private int command;

    @Column(name = "total_spend")
    private int total_spend;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "customers",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private List<AdresseCustomers> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customers",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private List<Order> Order = new ArrayList<>();
    public Customers(Boolean command_site, int command, int total_spend , User user) {
        this.command_site = command_site;
        this.command = command;
        this.total_spend = total_spend;
        this.user = user;
    }

    public Customers() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Boolean getCommand_site() {
        return command_site;
    }

    public void setCommand_site(Boolean command_site) {
        this.command_site = command_site;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getTotal_spend() {
        return total_spend;
    }

    public void setTotal_spend(int total_spend) {
        this.total_spend = total_spend;
    }

    public List<AdresseCustomers> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AdresseCustomers> addresses) {
        this.addresses = addresses;
    }

    public void addAddress(AdresseCustomers address) {
        addresses.add(address);
        address.setCustomers(this);
    }

    public void removeAddress(AdresseCustomers address) {
        addresses.remove(address);
        address.setCustomers((Customers) null);
    }
    public List<Order> getOrder() {
        return Order;
    }

    public void setOrder(List<Order> order) {
        Order = order;
    }

    ///
    @Override
    public String toString() {
        return "Customers{" +
                "customer_id=" + customer_id +
                ", command_site=" + command_site +
                ", command=" + command +
                ", total_spend=" + total_spend +
                "}\n";
    }
}
