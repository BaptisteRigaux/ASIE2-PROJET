import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderServiceService } from 'src/app/service/order-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-nav-bar-component',
  templateUrl: './nav-bar-component.component.html',
  styleUrls: ['./nav-bar-component.component.css']
})
export class NavBarComponentComponent implements OnInit {

  constructor(private router: Router, 
    private http: HttpClient, 
    private orderService: OrderServiceService,
    private snackBar: MatSnackBar) {}

  userEmail: string | null = null; // Variable pour stocker l'email de l'utilisateur
  userData: any; // Variable pour stocker les données utilisateur

  ngOnInit() {
    // Récupérer l'email utilisateur à partir du sessionStorage
    this.userEmail = sessionStorage.getItem('email');

    if (this.userEmail) {
      this.http.get(`http://localhost:8080/user/email=${this.userEmail}`).subscribe(
        (data: any) => {
          this.userData = data;
          console.log(this.userData)
        },
        (error) => {
          console.error('Erreur lors de la récupération des données utilisateur : ', error);
        }
      );
    }

    this.orderService.onArticleAddedToPanier.subscribe(() => {
      this.refreshPanierData();
    });
  }
  
  logout() {
    // Supprimer les données de session
    sessionStorage.removeItem('email'); // ou tout autre donnée de session

    // Rediriger vers la page de connexion
    this.router.navigateByUrl('/home');
    window.location.reload();
  }

  home() {
    this.router.navigateByUrl('/home');
  }

  // Cette méthode vérifie si l'utilisateur est connecté ou non
  isLoggedIn(): boolean {
    return !!sessionStorage.getItem('email'); // Vérifiez la présence des données de session
  }

  isSellerLogin(): boolean {
    return !!this.userData?.seller?.seller_id; // Vérifiez la présence du champ seller_id
  }
  redirectToManageArticle():void {
    this.router.navigateByUrl(`/manageSeller/${this.userData?.seller?.seller_id}`); // Remplacez '/login' par votre route de connexion
  }

  // Redirige l'utilisateur vers la page de connexion
  redirectToLogin(): void {
    this.router.navigateByUrl('/login'); // Remplacez '/login' par votre route de connexion
  }

  // Vérifie si l'utilisateur a des commandes
  hasOrders(): boolean {
    return !!this.userData.customers && this.userData.customers.order.length > 0;
  }

  hasCustomerId(): boolean {
    return !!this.userData?.customers?.customer_id;
  }

  redirectToOrders(): void {
    const customer_id = this.userData.customers?.customer_id;

    console.log(customer_id); // Assurez-vous que userId est correct
    if (customer_id) {
      this.router.navigateByUrl(`/users/${customer_id}/orders`);
      console.log("Redirection vers la page des commandes");
    } else {
      console.error('Impossible de récupérer l\'ID de l\'utilisateur.');
    }
  } 

  redirectToPersonalInfo(): void {

    const userId = this.userData?.user_id;
    const customerId = this.userData?.customers?.customer_id;
    console.log(userId); // Assurez-vous que userId est correct
    console.log(customerId);

    if (userId) {
      let route = `/users/${userId}/personalInfos`;

      if (customerId) {
        route += `/${customerId}`;
      }
      else{
        route += `/null`;
      }

      this.router.navigateByUrl(route);
      console.log("Redirection vers la page des informations personnelles");
    } else {
      console.error('Impossible de récupérer l\'ID de l\'utilisateur.');
    }
  } 

  redirectToAddArticle(): void {
    const userId = this.userData?.user_id;
    const SellerId = this.userData?.seller
    console.log(SellerId);
    console.log(SellerId?.seller_id);

    if (userId) {
      let route = `/AddArticle/${userId}`;
      
      if (SellerId) {
        route += `/${SellerId.seller_id}`;
      }
      else{
        route += `/null`;
      }
      this.router.navigateByUrl(route);
    }
  }

  getCartItemCount(): number {
    return this.userData?.panier?.articles.length || 0;
  }

  //Fonction pour vérifié si le panier est vide 
  isCartEmpty(): boolean {
    return this.userData?.panier?.articles.length === 0;
  }

  redirectToOrderPage(){
    const userId = this.userData?.user_id;
    const panierId = this.userData?.panier?.panier_id;

    if (panierId && !this.isCartEmpty()) {
      console.log(panierId);
      this.router.navigate(['/order', userId, panierId, 'command']);
    } else {
        // Utilisez MatSnackBar pour afficher un message
        this.snackBar.open('Vous ne pouvez pas consulter votre panier car il est vide. Ajoutez des articles avant.', 'Fermer', {
            duration: 3000 // Durée d'affichage du Snackbar en millisecondes
        });
    }

  }

  private refreshPanierData() {
    // Ici, vous pouvez rappeler votre API pour obtenir les données à jour du panier
    if (this.userEmail) {
      this.http.get(`http://localhost:8080/user/email=${this.userEmail}`).subscribe(
        (data: any) => {
          this.userData = data; // Mettez à jour les données de l'utilisateur, y compris les données du panier
        },
        (error) => {
          console.error('Erreur lors de la récupération des données utilisateur : ', error);
        }
      );
    }
  }

}
