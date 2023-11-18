package td.ecommerce.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "panier")
public class Panier {
    @Id
    @SequenceGenerator(
            name = "panier_sequence",
            sequenceName = "panier_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "panier_sequence")
    @Column(name = "panier_id")
    private Long panier_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "panier_articlehistory",
            joinColumns = @JoinColumn(name = "panier_id"),
            inverseJoinColumns = @JoinColumn(name = "articleprice_history_id")
    )
    private List<ArticlePriceHistory> articlePriceHistory = new ArrayList<>();

    public Panier(User user) {
        this.user = user;
    }
    public Panier(){
    }

    public Long getPanier_id() {
        return panier_id;
    }

    public void setPanier_id(Long panier_id) {
        this.panier_id = panier_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ArticlePriceHistory> getArticlePriceHistory() {
        return articlePriceHistory;
    }

    public void setArticlePriceHistory(List<ArticlePriceHistory> articlePriceHistory) {
        this.articlePriceHistory = articlePriceHistory;
    }
}
