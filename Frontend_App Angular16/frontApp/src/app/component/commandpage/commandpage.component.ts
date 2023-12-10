import { Component, OnInit } from '@angular/core';
import { OrderServiceService } from 'src/app/service/order-service.service';
import { ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

export interface Article {
  article_id: number;
  name_article: string;
  category: string;
  description: string;
  stock: number;
  price: number;
}

@Component({
  selector: 'app-commandpage',
  templateUrl: './commandpage.component.html',
  styleUrls: ['./commandpage.component.css']
})
export class CommandpageComponent implements OnInit{


  panierData: any;
  articles: Article[] = []; // Utilisez un tableau vide par défaut

  displayedColumns: string[] = ['name_article','catégory' , 'price' , 'actions'];

  constructor(
    private orderService: OrderServiceService, 
    private route: ActivatedRoute, 
    private http: HttpClient,
    private router: Router,
    ){
  }

  ngOnInit():void {
    this.getPanierData();
  }

  getPanierData() {

    const panierId = this.route.snapshot.params['panierId'];
    console.log(panierId);

    this.orderService.getPanier(panierId).subscribe(
      (data: any) => {
        if(data && data.articles){
           this.articles = data.articles;
           console.log(this.articles);
        }
        else {
          console.log("Aucune donnée d'article n'a été récupérée.")
        }
      },
      (error) => {
        console.log("Erreur lors de la récupréation du panier : " , error);
      });
  }
  
  onDeleteArticle( articleId: number) {
    const panierId = this.route.snapshot.params['panierId'];
    this.orderService.supprimerArticleDuPanier(panierId, articleId).subscribe(response => {
      console.log('Article supprimé', response);
      window.location.reload();
      // Actualiser les données du tableau ou gérer l'UI en conséquence
    }, error => {
      window.location.reload();
      console.error('Erreur lors de la suppression', error);
    });
  }

  commanderArticles() {

    console.log("Commande des articles", this.articles);
    const orderData = {
     articles: this.articles
    };
    console.log(orderData);

    const panierId = this.route.snapshot.params['panierId'];
    const userId = this.route.snapshot.params['userId'];
    console.log(panierId);
    console.log(userId);

    this.addOrder(userId, this.articles).subscribe({
      next: (response) => {
        console.log('Commande ajoutée avec succès', response);
        const customerId = response.customerId; // Récupérez l'ID du Customers
        this.router.navigate([`/users/${customerId}/orders`]); // Utilisez l'ID du Customers pour la redirection
      },
      error: (error) => {
        console.error('Erreur lors de l\'ajout de la commande', error);
      } 
    });

    // Vous pouvez ici ajouter la logique pour traiter la commande
   }


  private apiUrl = 'http://localhost:8080'; // URL de votre API

  addOrder(userId: number, articles: any[]): Observable<any> {

    return this.http.post(`${this.apiUrl}/users/${userId}/addOrder`, articles);
  }
}
