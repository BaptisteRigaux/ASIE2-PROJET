package td.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seller")
public class Seller {

    @Id
    @SequenceGenerator(
            name = "seller_sequence",
            sequenceName = "seller_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seller_sequence")
    @Column(name = "seller_id")
    private Long seller_id;

    @Column(name = "name_seller")
    private String name_seller;

    @Column(name = "email_contact_seller")
    private String email_seller;

    @Column(name = "phone_cotact_seller")
    private int phone_seller;

    @Column(name = "company_ID")
    private int company_ID;

    @Column(name = "adress_seller")
    private String adress_seller;

    @OneToMany(mappedBy = "seller",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private List<Article> articles = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Seller( String name_seller, String email_seller, int phone_seller, int company_ID, String adress_seller ,User user) {
        this.name_seller = name_seller;
        this.email_seller = email_seller;
        this.phone_seller = phone_seller;
        this.company_ID = company_ID;
        this.adress_seller = adress_seller;
        this.user = user;
    }

    public Seller(){

    }
    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public String getName_seller() {
        return name_seller;
    }

    public void setName_seller(String name_seller) {
        this.name_seller = name_seller;
    }

    public String getEmail_seller() {
        return email_seller;
    }

    public void setEmail_seller(String email_seller) {
        this.email_seller = email_seller;
    }

    public int getPhone_seller() {
        return phone_seller;
    }

    public void setPhone_seller(int phone_seller) {
        this.phone_seller = phone_seller;
    }

    public int getCompany_ID() {
        return company_ID;
    }

    public void setCompany_ID(int company_ID) {
        this.company_ID = company_ID;
    }

    public String getAdress_seller() {
        return adress_seller;
    }

    public void setAdress_seller(String adress_seller) {
        this.adress_seller = adress_seller;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
