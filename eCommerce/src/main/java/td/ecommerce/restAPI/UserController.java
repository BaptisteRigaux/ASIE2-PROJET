package td.ecommerce.restAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import td.ecommerce.model.*;
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


    @Autowired UserController(User_Service userService, Customers_Service customerService , Seller_Service sellerService ,
                              Article_Service articleService ,ArticlePriceHistory_Service articlePriceHistoryService ,Order_Service orderService ,
                              AdresseCustomers_Service adresseCustomersService , Panier_Service panierService){
        this.userService =userService;
        this.customerService = customerService;
        this.sellerService = sellerService;
        this.articleService = articleService;
        this.articlePriceHistoryService = articlePriceHistoryService;
        this.orderService = orderService;
        this.adresseCustomersService = adresseCustomersService;
        this.panierService = panierService ;
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

    @PostMapping("/user/{userId}/change")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){

        if (!userId.equals(user.getUser_id())) {
            // Si l'ID dans l'URL ne correspond pas à celui de l'utilisateur dans le corps de la requête
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User usertochange = userService.updateUser(user);
        return new ResponseEntity<>(usertochange, HttpStatus.OK);
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
