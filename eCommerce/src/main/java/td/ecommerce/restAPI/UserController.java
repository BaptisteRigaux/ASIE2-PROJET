package td.ecommerce.restAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import td.ecommerce.model.*;
import td.ecommerce.repository.AdresseCustomers_Repository;
import td.ecommerce.repository.ArticlePriceHistory_Repostory;
import td.ecommerce.repository.Article_Repository;
import td.ecommerce.repository.Customers_Repository;
import td.ecommerce.repository.Order_Repository;
import td.ecommerce.repository.Panier_Repository;
import td.ecommerce.repository.Seller_Repository;
import td.ecommerce.repository.User_Repository;
import td.ecommerce.service.*;

import java.util.*;

@RestController
@RequestMapping("")
public class UserController {

    private User_Service userService;
    private Customers_Service customerService;
    private Seller_Service sellerService;
    private Article_Service articleService;
    private ArticlePriceHistory_Service articlePriceHistoryService;
    private Order_Service orderService;
    private AdresseCustomers_Service adresseCustomersService;
    private Panier_Service panierService;

    private final AdresseCustomers_Repository addressRepository;
    private final Customers_Repository customersRepository;
    private final Seller_Repository sellerRepository;
    private final Article_Repository articleRepository;
    private final User_Repository userRepository;
    private final Order_Repository orderRepository;
    private final Panier_Repository panierRepository;
    private final ArticlePriceHistory_Repostory articlePriceHistoryRepostory;


    @Autowired UserController(User_Service userService, Customers_Service customerService , Seller_Service sellerService ,
                              Article_Service articleService ,ArticlePriceHistory_Service articlePriceHistoryService ,Order_Service orderService ,
                              AdresseCustomers_Service adresseCustomersService , Panier_Service panierService ,AdresseCustomers_Repository addressRepository ,Customers_Repository customersRepository,
                              Seller_Repository sellerRepository , Article_Repository articleRepository ,User_Repository userRepository , Order_Repository orderRepository,
                              Panier_Repository panierRepository, ArticlePriceHistory_Repostory articlePriceHistoryRepostory ){
        this.userService =userService;
        this.customerService = customerService;
        this.sellerService = sellerService;
        this.articleService = articleService;
        this.articlePriceHistoryService = articlePriceHistoryService;
        this.orderService = orderService;
        this.adresseCustomersService = adresseCustomersService;
        this.panierService = panierService ;
        this.addressRepository =addressRepository;
        this.customersRepository=customersRepository;
        this.sellerRepository = sellerRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.panierRepository = panierRepository;
        this.articlePriceHistoryRepostory = articlePriceHistoryRepostory;
    }


    //Api permetttant de recupup les user 
    //Le login
    //Put les informations d'edition d'un utilisateur

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> listeUsers = userService.getAllUsers();
        return new ResponseEntity<List<User>>(listeUsers,HttpStatus.CREATED);
    }

    @GetMapping("/user/email={email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    User user = userService.getUserByEmail(email);
    if (user != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    //Route pour changer les informations personnel d'un utilisateur
    @PostMapping("/user/{userId}/change")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){

        if (!userId.equals(user.getUser_id())) {
            // Si l'ID dans l'URL ne correspond pas à celui de l'utilisateur dans le corps de la requête
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User usertochange = userService.updateUser(user);
        return new ResponseEntity<>(usertochange, HttpStatus.OK);
    }

    //Route pour avoir les orders d'un customer par customer_id
    @GetMapping("/users/{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomers(@PathVariable Long customerId) {
        List<Order> orders = orderService.getOrdersByCustomerId(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    //Route pour avoir les adresse d'un customer par customer_id
    @GetMapping("/customers/{customerId}/adress")
    public ResponseEntity<List<AdresseCustomers>> getAdressByCustomers(@PathVariable Long customerId)
    {
        List<AdresseCustomers> listAdresseCustomers = adresseCustomersService.getAddressesByCustomersId(customerId);
        return new ResponseEntity<>(listAdresseCustomers,HttpStatus.CREATED);
    }

    //route pour ajouter une adresse sur un CustomerID
    @PostMapping("/addAdress/{customerId}")
    public ResponseEntity<String> addAddressToCustomer(@PathVariable Long customerId, @RequestBody AdresseCustomers address) {
        Optional<Customers> optionalCustomer = customersRepository.findById(customerId);
        
        if (optionalCustomer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Customers customer = optionalCustomer.get();
        address.setCustomers(customer);
        addressRepository.save(address);
        
        return ResponseEntity.ok("Address added successfully for customer with ID: " + customerId);
    }

    //route pour delete des adresse par leur addressId
    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<String> deleteAddressById(@PathVariable Long addressId) {
        try {
        adresseCustomersService.deleteAddressById(addressId);
        return new ResponseEntity<>("Suppression OK", HttpStatus.OK);
        } catch (EntityNotFoundException  e) {
        return new ResponseEntity<>("L'adresse n'a pas pu être trouvée pour la suppression", HttpStatus.NOT_FOUND);
        }
    }

    //route pour ajouter un article en étant déjà un seller
    @PostMapping("/addArticle/{sellerId}")
    public ResponseEntity<Object> addArticleToSeller(@PathVariable Long sellerId, @RequestBody Article article) {
        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
        System.err.println(optionalSeller);
        
        if (optionalSeller.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        Seller seller = optionalSeller.get();
        article.setSeller(seller);
        Article savedArticle = articleRepository.save(article);

        ArticlePriceHistory articlePriceHistory = new ArticlePriceHistory();
        articlePriceHistory.setPrice_article(savedArticle.getPrice());
        articlePriceHistory.setPrice_start(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 25);
        articlePriceHistory.setDate_end(calendar.getTime()); // Date de fin à maintenant + 25 jours
        articlePriceHistory.setArticle(savedArticle);

        articlePriceHistoryRepostory.save(articlePriceHistory);

        // Créez un objet JSON pour renvoyer une réponse plus structurée
        Map<String, String> response = new HashMap<>();
        response.put("message", "Article added successfully for Seller with ID: " + sellerId);
        
        return ResponseEntity.ok(response);
    }

    //route pour créer un seller 
    @PostMapping("/addSeller/{userId}")
    public ResponseEntity<Seller> addSellerToUser(@PathVariable Long userId , @RequestBody Seller seller) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();
        seller.setUser(user);
        Seller savedSeller = sellerRepository.save(seller);

        return ResponseEntity.ok(savedSeller);
    }

    //Route pour avoir les Articles d'un sellerId
    @GetMapping("/seller/{sellerId}/articles")
    public ResponseEntity<List<Article>> getArticlesBySellers(@PathVariable Long sellerId)
    {
        List<Article> listArticleSeller = articleService.getArticlesBySeller(sellerId);
        return new ResponseEntity<>(listArticleSeller,HttpStatus.CREATED);
    }

    //Route pour update un Article
    @PutMapping("/api/updateArticle/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long articleId,@RequestBody Article updatedArticle)
    {
        
        Optional<Article> optionalArticle = articleRepository.findById(articleId);

        if (optionalArticle.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Article existingArticle = optionalArticle.get();

        // Vérifier si le prix a changé
        if (existingArticle.getPrice() != updatedArticle.getPrice()) {
            existingArticle.setPrice(updatedArticle.getPrice());

            // Créer un nouvel ArticlePriceHistory si le prix a changé
            ArticlePriceHistory newPriceHistory = new ArticlePriceHistory();
            newPriceHistory.setPrice_article(updatedArticle.getPrice());
            newPriceHistory.setPrice_start(new Date()); // Date actuelle
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 25); // Ajouter 25 jours
            newPriceHistory.setDate_end(calendar.getTime()); // Date de fin à maintenant + 25 jours
            newPriceHistory.setArticle(existingArticle); // Associer avec l'article mis à jour

            // Sauvegarder le nouvel historique des prix
            articlePriceHistoryRepostory.save(newPriceHistory);
        }

        existingArticle.setName_article(updatedArticle.getName_article());
        existingArticle.setDescription(updatedArticle.getDescription());
        existingArticle.setPrice(updatedArticle.getPrice());
        existingArticle.setStock(updatedArticle.getStock());
        existingArticle.setCatégory(updatedArticle.getCatégory());

        Article articletochange = articleRepository.save(existingArticle);
        return new ResponseEntity<>(articletochange, HttpStatus.OK);
        
    }

    //Route pour delete un Article
    @DeleteMapping("/deleteArticle/{articleId}")
    public ResponseEntity<String> deleteArticleById(@PathVariable Long articleId) {
        try {
        articleService.deleteArticleById(articleId);
        return new ResponseEntity<>("Suppression OK", HttpStatus.OK);
        } catch (EntityNotFoundException  e) {
        return new ResponseEntity<>("L'adresse n'a pas pu être trouvée pour la suppression", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/orders/{orderId}/articlePriceHistories")
    public ResponseEntity<List<ArticlePriceHistory>> getArticlePriceHistoriesByOrderId(@PathVariable Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            List<ArticlePriceHistory> articlePriceHistories = order.getArticlePriceHistories();
            return new ResponseEntity<>(articlePriceHistories, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Permets d'ajouter des article sur un utilisateur ou un panier en fonction de si un existe déjà
    @PostMapping("/addToPanier/{panierId}/{userId}")
    public ResponseEntity<Map<String, String>> addArticleToPanier(@PathVariable(required = false) String panierId, @PathVariable Long userId, @RequestBody Article article) {
        Optional<User> optionalUser = userRepository.findById(userId);
        
        if (optionalUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        User user = optionalUser.get();
        Panier panier;

        // Vérification si le panier à été créer entre temps
        Optional<Panier> existingPanierOptional = panierRepository.findByUserId(userId);
        if (existingPanierOptional.isPresent()) {
            panier = existingPanierOptional.get();
        } else if (panierId == null || panierId.equals("null")) {
            // Créez un nouveau panier si l'utilisateur n'en a pas et panierId est null
            panier = new Panier(user);
        } else {
            // Vérifiez si le panier existe pour l'ID donné
            Long panierIdLong = Long.parseLong(panierId);
            Optional<Panier> optionalPanier = panierRepository.findById(panierIdLong);
            if (optionalPanier.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            panier = optionalPanier.get();
        }
        // Ajoutez l'article au panier de l'utilisateur
        panier.getArticles().add(article);

        // Mettez à jour l'utilisateur dans la base de données
        panierRepository.save(panier);
        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Article ajouté avec succès au panier de l'utilisateur.");
        return ResponseEntity.ok(response);
    }

    //Route pour avoir les Articles d'un sellerId
    @GetMapping("/order/{panierId}")
    public ResponseEntity<Panier> getPanierById(@PathVariable Long panierId)
    {
        Optional<Panier> optionalPanier = panierRepository.findById(panierId);
        
        if (optionalPanier.isEmpty()) {
            return ResponseEntity.notFound().build();
            
        }

        Panier panier = optionalPanier.get();
        return ResponseEntity.ok(panier);
    }




    //supprimé un article du panier connu
    // UserController.java
    @DeleteMapping("/panier/{panierId}/article/{articleId}")
    public ResponseEntity<String> deleteArticleFromPanier(@PathVariable Long panierId, @PathVariable Long articleId) {
        try {
            Panier panier = panierService.getPanierById(panierId);
            Article articleToRemove = articleService.getArticleById(articleId);

            if (panier == null || articleToRemove == null) {
                return new ResponseEntity<>("Panier ou Article introuvable", HttpStatus.NOT_FOUND);
            }

            panier.getArticles().remove(articleToRemove);
            panierRepository.save(panier);

            return new ResponseEntity<>("Article supprimé du panier", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur lors de la suppression de l'article du panier", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Créer un achat pour utilsateur qui à déjà acheter : 
    @PostMapping("/users/{userId}/addOrder")
    public ResponseEntity<Map<String, Object>> addOrderToCustomer(@PathVariable Long userId, @RequestBody List<Article> articles) {
        
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        Customers customer = user.getCustomers();

        if (customer == null) {
            customer = new Customers();
            customer.setUser(user);
            customer.setCommand_site(true);
            customer.setCommand(0); // Définissez le nombre initial de commandes
            customer.setTotal_spend(0); // Définissez la dépense totale initiale
            // Sauvegardez le nouveau Customers
            customersRepository.save(customer);
            user.setCustomers(customer); // Associez le Customers à l'utilisateur
            userRepository.save(user); // Mettez à jour l'utilisateur
        }

        // Obtenir le dernier numéro de commande
        Integer lastOrderNumber = orderRepository.findMaxOrderNumber();
        if (lastOrderNumber == null) {
            lastOrderNumber = 0; // Si aucune commande n'existe, commencez à partir de 1
        }
        
        System.out.println(customer);
        Order newOrder = new Order();
        newOrder.setCustomers(customer);
        newOrder.setNumber_order(lastOrderNumber + 1);
        newOrder.setStatus_order("En attente");
        newOrder.setDate_order(new Date()); // Utilisez la date actuelle pour la commande

        System.out.println(newOrder);

        int totalAmount = 0;
        List<ArticlePriceHistory> articlePriceHistories = new ArrayList<>();
        for (Article article : articles) {


            System.out.println(article.getArticle_id());
            ArticlePriceHistory latestHistory = articleService.getLatestPriceHistoryForArticle(article.getArticle_id());
            System.out.println("test " +  latestHistory);

            if (latestHistory != null) {
                articlePriceHistories.add(latestHistory);
                totalAmount += latestHistory.getPrice_article(); // Calcul du montant total
            } else {
                // Gérer le cas où l'historique des prix n'est pas trouvé pour un article
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(null); // Ou gérer de manière plus détaillée
            }
        }
        newOrder.setArticlePriceHistories(articlePriceHistories);
        newOrder.setTotal_amount(totalAmount); // Définir le montant total

        try {
            Order savedOrder = orderRepository.save(newOrder);
            panierService.clearUserPanier(userId); // Supprimez les articles du panier

            // Créez une map pour la réponse
            Map<String, Object> response = new HashMap<>();
            response.put("order", savedOrder);
            response.put("customerId", customer.getCustomer_id()); // Inclure l'ID du Customers
    
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            // Gérer l'exception en cas d'échec de l'ajout de la commande
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);
        }
    }

    







    @GetMapping("/allcustomers")
    public ResponseEntity<List<Customers>> getAllCustomers()
    {
        List<Customers> listeCustomers = customerService.getAllCustomers();
        return new ResponseEntity<List<Customers>>(listeCustomers,HttpStatus.CREATED);
    }


    



    @GetMapping("/allseller")
    public ResponseEntity<List<Seller>> getAllSeller()
    {
        List<Seller> listSeller = sellerService.getAllSeller();
        return new ResponseEntity<List<Seller>>(listSeller,HttpStatus.CREATED);
    }

    @GetMapping("/allArticles")
    public ResponseEntity<List<Article>> getAllArticle()
    {
        List<Article> listArticle = articleService.getAllArticles();
        return new ResponseEntity<List<Article>>(listArticle,HttpStatus.CREATED);
    }

    @GetMapping("/allArticlesPriceHistory")
    public ResponseEntity<List<ArticlePriceHistory>> getAllArticlePriceHistory()
    {
        List<ArticlePriceHistory> listArticlePrice = articlePriceHistoryService.getAllArticlesPriceHistory();
        return new ResponseEntity<List<ArticlePriceHistory>>(listArticlePrice,HttpStatus.CREATED);
    }

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders()
    {
        List<Order> listOrder = orderService.getAllOrders();
        return new ResponseEntity<List<Order>>(listOrder,HttpStatus.CREATED);
    }

    @GetMapping("/allAdresseCustomers")
    public ResponseEntity<List<AdresseCustomers>> getAllAddresses()
    {
        List<AdresseCustomers> listAdresseCustomers = adresseCustomersService.getAllAddresses();
        return new ResponseEntity<List<AdresseCustomers>>(listAdresseCustomers,HttpStatus.CREATED);
    }

    @GetMapping("/allPanier")
    public ResponseEntity<List<Panier>> getPanier()
    {
        List<Panier> listPanier = panierService.getAllPanier();
        return new ResponseEntity<List<Panier>>(listPanier,HttpStatus.CREATED);
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User usertoadd = userService.persistUser(user);
        return new ResponseEntity<User>(usertoadd,HttpStatus.CREATED);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long idUserToDelete){

        userService.deleteUser(idUserToDelete);
        return new ResponseEntity<String>("Suppresion de l'utilsateur " ,HttpStatus.OK);
    }


//    @GetMapping("/testViews")
//    public ResponseEntity<List<Customers>> testCustomersView()
//    {
//        List<Customers> listeCustomers = customerService.getAllCustomers();
//        return new ResponseEntity<List<Customers>>(listeCustomers,HttpStatus.CREATED);
//    }


//    @GetMapping("/testViews")
//    public  ResponseEntity<List<User>> getUsers(@JsonView(ViewUser.SellerUser.class) User user) {
//        List<User> listeCustomers = userService.getAllUsers();
//        return new ResponseEntity<List<User>>(listeCustomers,HttpStatus.CREATED);
//    }
//

    // Lors de la connexion , je veux vérifié si un utilsiteur utilise le bon mdp face à son email
    // Je ne dois pas lister la liste des utilisateurs

    // Modifier - delete à faire , GET ( toutes les tables )
}
