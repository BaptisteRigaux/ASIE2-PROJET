package td.ecommerce.restAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import td.ecommerce.model.*;
import td.ecommerce.repository.AdresseCustomers_Repository;
import td.ecommerce.repository.Article_Repository;
import td.ecommerce.repository.Customers_Repository;
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


    @Autowired UserController(User_Service userService, Customers_Service customerService , Seller_Service sellerService ,
                              Article_Service articleService ,ArticlePriceHistory_Service articlePriceHistoryService ,Order_Service orderService ,
                              AdresseCustomers_Service adresseCustomersService , Panier_Service panierService ,AdresseCustomers_Repository addressRepository ,Customers_Repository customersRepository,
                              Seller_Repository sellerRepository , Article_Repository articleRepository ,User_Repository userRepository ){
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
        articleRepository.save(article);
        
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
