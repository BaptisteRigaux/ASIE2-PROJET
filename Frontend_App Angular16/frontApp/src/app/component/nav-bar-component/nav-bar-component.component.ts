import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar-component',
  templateUrl: './nav-bar-component.component.html',
  styleUrls: ['./nav-bar-component.component.css']
})
export class NavBarComponentComponent implements OnInit {

  constructor(private router: Router, private http: HttpClient) {}

  userEmail: string | null = null; // Variable pour stocker l'email de l'utilisateur
  userData: any; // Variable pour stocker les données utilisateur

  ngOnInit() {
    // Récupérer l'email utilisateur à partir du sessionStorage
    this.userEmail = sessionStorage.getItem('email');

    if (this.userEmail) {
      this.http.get(`http://localhost:8080/user/email=${this.userEmail}`).subscribe(
        (data: any) => {
          this.userData = data;
        },
        (error) => {
          console.error('Erreur lors de la récupération des données utilisateur : ', error);
        }
      );
    }
  }
  
  logout() {
    // Supprimer les données de session
    sessionStorage.removeItem('email'); // ou tout autre donnée de session

    // Rediriger vers la page de connexion
    this.router.navigateByUrl('/home');
    window.location.reload();
  }

  home() {
    this.router.navigateByUrl('//home');
  }

  // Cette méthode vérifie si l'utilisateur est connecté ou non
  isLoggedIn(): boolean {
    return !!sessionStorage.getItem('email'); // Vérifiez la présence des données de session
  }

  // Redirige l'utilisateur vers la page de connexion
  redirectToLogin(): void {
    this.router.navigateByUrl('/login'); // Remplacez '/login' par votre route de connexion
  }

  // Vérifie si l'utilisateur a des commandes
  hasOrders(): boolean {
    return !!this.userData.customers && this.userData.customers.order.length > 0;
  }

  redirectToOrders(): void {
    const userId = this.userData?.user_id;
    console.log(userId); // Assurez-vous que userId est correct
    if (userId) {
      this.router.navigateByUrl(`/users/${userId}/orders`);
      console.log("Redirection vers la page des commandes");
    } else {
      console.error('Impossible de récupérer l\'ID de l\'utilisateur.');
    }
  } 
}
