import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { OrderServiceService } from "src/app/service/order-service.service";
import { SericeAuthService } from 'src/app/service/serice-auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';


@Component({
  selector: 'app-orders-cards',
  templateUrl: './orders-cards.component.html',
  styleUrls: ['./orders-cards.component.css']
})
export class OrdersCardsComponent implements OnInit{
  articles: any;
  userData: any;
  SericeAuthService: any;
  userEmail!: string | null ;

  constructor(private orderService: OrderServiceService,
    private http: HttpClient,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private router: Router,
    ){}

  ngOnInit(): void {
    this.orderService.getAllArticles().subscribe(
      (data: any[]) => {
        this.articles = data; // Assigner les données d'articles au tableau d'articles
        console.log(this.articles);
      },
      (error) => {
        console.error('Une erreur est survenue lors de la récupération des articles : ', error);
      }
    );

    this.userEmail = sessionStorage.getItem('email');

    if (this.userEmail) {
      this.http.get(`http://localhost:8080/user/email=${this.userEmail}`).subscribe(
        (data: any) => {
          this.userData = data;
          console.log(this.userData)
        },
        (error) => {
          console.error('Aucun user connecté', error);
        }
      );
    }
  }  

  addToCart(article: any): void {
    const userId = this.userData?.user_id;
    const panierId = this.userData?.panier?.panier_id;

    if (!this.isLoggedIn()) {
      // Utiliser confirm() pour demander à l'utilisateur s'il souhaite s'inscrire
      if (confirm("Vous devez être connecté pour ajouter des articles au panier. Voulez-vous vous inscrire ?")) {
        this.router.navigate(['/registrer']); // Rediriger vers la page d'inscription
      }
    } else {
      // Envoyer la demande d'ajout d'article au panier
      this.orderService.addToPanier(panierId, userId, article).subscribe(
        (response: any) => {
          console.log('Article ajouté avec succès au panier !');
          this.snackBar.open('Article ajouté avec succès au panier !', 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
          });
          this.orderService.onArticleAddedToPanier.emit(); // Déclencher l'événement
        },
        (error) => {
          console.error('Erreur lors de l\'ajout de l\'article au panier : ', error);
          this.snackBar.open('Erreur lors de l\'ajout de l\'article au panier.', 'Fermer', {
            duration: 3000,
            horizontalPosition: 'center',
            verticalPosition: 'bottom',
          });
        }
      );
    }
  }

  isLoggedIn(): boolean {
    // Implémentez votre logique de vérification de connexion ici
    return !!sessionStorage.getItem('email');
  }
}
