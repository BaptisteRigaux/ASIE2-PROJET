package td.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_sequence")
    @Column(name = "article_id")
    private Long article_id;

    @Column(name = "name_article")
    private String name_article;

    @Column(name = "catégory")
    private String  catégory;

    @Column(name = "description")
    private String description;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price_article")
    private int price;

    // @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL} )
    //private List<Article> articles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToMany(mappedBy = "article",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private List<ArticlePriceHistory> articlePriceHistories = new ArrayList<>();

    public Article(String name_article, String catégory, String description, int stock, int price , Seller seller) {
        this.name_article = name_article;
        this.catégory = catégory;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.seller = seller;
        seller.getArticles().add(this);
    }

    public Article(){}

    public List<ArticlePriceHistory> getArticlePriceHistories() {
        return articlePriceHistories;
    }

    public void setArticlePriceHistories(List<ArticlePriceHistory> articlePriceHistories) {
        this.articlePriceHistories = articlePriceHistories;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public String getName_article() {
        return name_article;
    }

    public void setName_article(String name_article) {
        this.name_article = name_article;
    }

    public String getCatégory() {
        return catégory;
    }

    public void setCatégory(String catégory) {
        this.catégory = catégory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
       
    }

}
