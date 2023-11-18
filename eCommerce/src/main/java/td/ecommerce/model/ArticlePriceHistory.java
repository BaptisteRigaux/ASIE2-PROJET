package td.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article_price_history")
public class ArticlePriceHistory {

    @Id
    @SequenceGenerator(
            name = "article_price_history_sequence",
            sequenceName = "article_price_history_sequence",
            allocationSize = 1
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_price_history_sequence")
    @Column(name = "articleprice_history_id")
    private Long articleprice_history_id;

    @Column(name = "price_article")
    private int price_article;

    @Column(name = "date_start")
    private Date price_start;

    @Column(name = "date_end")
    private Date date_end;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "panier_articlehistory",
            joinColumns = @JoinColumn(name = "articleprice_history_id"),
            inverseJoinColumns = @JoinColumn(name = "panier_id")
    )
    private List<Panier> paniers = new ArrayList<>();

    public ArticlePriceHistory(int price_article, Date price_start, Date date_end, Article article) {
        this.price_article = price_article;
        this.price_start = price_start;
        this.date_end = date_end;
        this.article = article;
    }
    public ArticlePriceHistory(){

    }
    public Long getArticleprice_history_id() {
        return articleprice_history_id;
    }

    public void setArticleprice_history_id(Long articleprice_history_id) {
        this.articleprice_history_id = articleprice_history_id;
    }

    public int getPrice_article() {
        return price_article;
    }

    public void setPrice_article(int price_article) {
        this.price_article = price_article;
    }

    public Date getPrice_start() {
        return price_start;
    }

    public void setPrice_start(Date price_start) {
        this.price_start = price_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public void setDate_end(Date date_end) {
        this.date_end = date_end;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Panier> getPaniers() {
        return paniers;
    }

    public void setPaniers(List<Panier> paniers) {
        this.paniers = paniers;
    }
}
