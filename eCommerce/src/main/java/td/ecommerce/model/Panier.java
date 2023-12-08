package td.ecommerce.model;

import jakarta.persistence.*;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name = "panier_article",
            joinColumns = @JoinColumn(name = "panier_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles = new ArrayList<>();

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

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticle(List<Article> articles) {
        this.articles = articles;
    }
}
