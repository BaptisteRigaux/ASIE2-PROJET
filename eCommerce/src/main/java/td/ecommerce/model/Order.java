package td.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "order_id")
    private Long order_id;

    @Column(name = "status_order")
    private String status_order;

    @Column(name = "total_amount")
    private int totalAmount;

    @Column(name = "date_order")
    private Date date_order;

    @Column(name = "Number_order")
    private int number_order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customers customers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "orders_articleshistory",
            joinColumns = @JoinColumn(name = "order_order_id"),
            inverseJoinColumns = @JoinColumn(name = "articles_article_id")
    )
    private List<Article> articles = new ArrayList<>();

    public Order(String status_order, int totalAmount, Date date_order, int number_order, Customers customers) {
        this.status_order = status_order;
        this.totalAmount = totalAmount;
        this.date_order = date_order;
        this.number_order = number_order;
        this.customers = customers;
        customers.getOrder().add(this);
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getStatus_order() {
        return status_order;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public int getTotal_amount() {
        return totalAmount;
    }

    public void setTotal_amount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate_order() {
        return date_order;
    }

    public void setDate_order(Date date_order) {
        this.date_order = date_order;
    }

    public int getNumber_order() {
        return number_order;
    }

    public void setNumber_order(int number_order) {
        this.number_order = number_order;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }


    public Order() {
        super();
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
