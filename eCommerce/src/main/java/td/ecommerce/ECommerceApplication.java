package td.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import td.ecommerce.model.*;
import td.ecommerce.repository.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }

    //@Bean
    CommandLineRunner ajouterAdresse_Costumers(AdresseCustomers_Repository adresseCustomersRepository){
        return args -> {
            adresseCustomersRepository.deleteAll();
            ArrayList<AdresseCustomers> listeGG = new ArrayList<AdresseCustomers>();
            listeGG.add((new AdresseCustomers("156 rue laurendeau",
                    "Amiens",80000,"France",null)));
            listeGG.add((new AdresseCustomers("15 rue de paris",
                    "Amiens",80000,"France",null)));

            //On insère la liste dans la table
            adresseCustomersRepository.saveAll(listeGG);

            listeGG = new ArrayList<AdresseCustomers>();
            System.out.print( "⚠️⚠️" + adresseCustomersRepository.findAll() + "⚠️⚠️");

        };
    }


    //Bean de création pour tester le modèle
    @Bean
    CommandLineRunner seeData(AdresseCustomers_Repository adresseCustomersRepository,
                              Customers_Repository customersRepository ,
                              Order_Repository orderRepository ,
                              Article_Repository articleRepository ,
                              Seller_Repository sellerRepository,
                              User_Repository userRepository,
                              Panier_Repository panierRepository,
                              ArticlePriceHistory_Repostory articlePriceHistoryRepostory){
        return args -> {
            //On Clean tous
            adresseCustomersRepository.deleteAll();
            customersRepository.deleteAll();
            articlePriceHistoryRepostory.deleteAll();
            articleRepository.deleteAll();
            orderRepository.deleteAll();
            sellerRepository.deleteAll();
            userRepository.deleteAll();
            panierRepository.deleteAll();
            

        
            List<Article> articles = new ArrayList<>();
        
            //On créer des users
            User user1 = new User("John", "Doe", "john@example.com", LocalDate.of(1990, 5, 15), "male", new Date(), "pass123");
            User user2 = new User("Bastien", "Moril", "Bastien@example.com", LocalDate.of(2023, 8, 22), "female", new Date(), "pass123" );
            User user3 = new User("Louis", "safran", "Louis@example.com", LocalDate.of(2023, 9, 30),"female", new Date(), "pass123" );
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            //On créer des acheteurs
            Customers customers1 = new Customers(false , 0 ,0 , user3);
            customersRepository.save(customers1);
            Customers customers2 = new Customers(false , 0 ,0 ,user2);
            customersRepository.save(customers2);

            //On ajout des adresse d'acheteur
            customers1 = customersRepository.findById(customers1.getCustomer_id()).orElseThrow();
            AdresseCustomers adresseCustomers1 = new AdresseCustomers("156 rue laurendeau",
                    "Amiens",80000,"france",customers1);
            adresseCustomersRepository.save(adresseCustomers1);

            customers2 = customersRepository.findById(customers2.getCustomer_id()).orElseThrow();
            AdresseCustomers adresseCustomers2 = new AdresseCustomers("156 rue laurendeau",
                    "Amiens",80000,"france",customers2);
            adresseCustomersRepository.save(adresseCustomers2);

            //On créer des Seller
            Seller seller1 = new Seller("Baptistephone","baptiste.rigaux@yahoo.com",0605,10012 , "10 amiens avenue des champs",user1);
            Seller seller2 = new Seller("Seller2","seller2@yahoo.com",0105030406,10013 , "15 route de paris" ,user2);
            sellerRepository.save(seller1);
            sellerRepository.save(seller2);

            //On Créer des Articles associé à des sellers
            seller1 = sellerRepository.findById(seller1.getSeller_id()).orElseThrow();
            Article Article1 = new Article("Premier article" ,"Jeux vidéo" ,"Spécial ordinateur", 10 ,26,seller1);
            articleRepository.save(Article1);

            Article Article2 = new Article("Deuxieme article" ,"Carte cadeau" ,"Carte", 15 ,15,seller1);
            articleRepository.save(Article2);

            seller2 = sellerRepository.findById(seller2.getSeller_id()).orElseThrow();
            Article Article3 = new Article("Troisieme article" ,"Console" ,"Console de jeux video", 20 ,500,seller2);
            articleRepository.save(Article3);

            //On créer des prices d'articles
            Article article1 = articleRepository.findById(Article1.getArticle_id()).orElseThrow();
            Article article2 = articleRepository.findById(Article2.getArticle_id()).orElseThrow();
            ArticlePriceHistory articlePriceHistory1 = new ArticlePriceHistory(150,new Date(),new Date(),article1);
            ArticlePriceHistory articlePriceHistory2 = new ArticlePriceHistory(198,new Date(),new Date(),article2);
            ArticlePriceHistory articlePriceHistory3 = new ArticlePriceHistory(155,new Date(),new Date(),article1);
            articlePriceHistoryRepostory.save(articlePriceHistory1);
            articlePriceHistoryRepostory.save(articlePriceHistory2);
            articlePriceHistoryRepostory.save(articlePriceHistory3);

            //On créer des Commandes
            Order order1 = new Order( "waiting" , 150 ,new Date() ,10005 ,customers1);
            orderRepository.save(order1);
            Order order2 = new Order("done" , 250 ,new Date() ,10006 ,customers2);
            orderRepository.save(order2);

            //Ajouter des articles aux commandes
            order1.getArticlePriceHistories().add(articlePriceHistory1);
            order1.getArticlePriceHistories().add(articlePriceHistory2);
            order2.getArticlePriceHistories().add(articlePriceHistory1);
            order2.getArticlePriceHistories().add(articlePriceHistory3);

            //Créer une liste d'article
           // Article article1 = articleRepository.findById(Article1.getArticle_id()).orElseThrow(); // Assurez-vous d'avoir l'ID correct.
            articles.add(article1);

           // Article article2 = articleRepository.findById(Article2.getArticle_id()).orElseThrow(); // Assurez-vous d'avoir l'ID correct.
            articles.add(article2);

            Panier panier1 = new Panier(user1);
            List<Article> articlesPanier = articleRepository.findAll();
            panier1.getArticles().addAll(articlesPanier);
            panierRepository.save(panier1);

            // Enregistrer les modifications
            orderRepository.save(order1);
            orderRepository.save(order2);
        };
    }

    //@Bean
    CommandLineRunner test(Article_Repository articleRepository){
        return args -> {

            Long ArticletoDelete = 5L;
            Optional<Article> Testchoix5 = articleRepository.findById(ArticletoDelete);
            System.out.print("⚠️⚠️" + Testchoix5 + "⚠️⚠️" );

            articleRepository.deleteById(ArticletoDelete);
            System.out.print( "⚠️⚠️" + articleRepository.findAll() + "⚠️⚠️");

            Article article1 = articleRepository.findById(5l).orElseThrow();
            System.out.print("⚠️⚠️" + article1 + "⚠️⚠️" );

            articleRepository.delete(article1);
            System.out.print( "⚠️⚠️" + articleRepository.findAll() + "⚠️⚠️");


        };
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        CorsFilter corsFilter = new CorsFilter(source);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(corsFilter);
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
