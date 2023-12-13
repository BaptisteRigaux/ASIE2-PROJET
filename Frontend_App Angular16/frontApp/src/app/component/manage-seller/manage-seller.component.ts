import { HttpClient } from '@angular/common/http';
import { Component , OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EditArticleDialogComponent } from '../edit-article-dialog/edit-article-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-manage-seller',
  templateUrl: './manage-seller.component.html',
  styleUrls: ['./manage-seller.component.css']
})
export class ManageSellerComponent implements OnInit{
  articles: any[] = []; // Supposons que vos articles sont stockés ici
  sellerId: number | null = null;

  constructor(private http: HttpClient ,private route: ActivatedRoute, private dialog: MatDialog , private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const sellerIdParam = params.get('sellerId');
      this.sellerId = sellerIdParam ? +sellerIdParam : null;
      if (this.sellerId) {
        this.loadSellerArticles(this.sellerId);
      } else {
        console.error('ID du vendeur non trouvé dans l\'URL');
      }
    });
  }

  loadSellerArticles(sellerId: number) {
    
    // Remplacez par votre logique pour charger les articles du vendeur depuis votre API
    this.http.get<any[]>(`http://localhost:8080/seller/${sellerId}/articles`).subscribe(
      (data) => {
        this.articles = data; // Mettez à jour votre liste d'articles avec les données reçues
        console.log(this.articles)
      },
      (error) => {
        console.error('Erreur lors du chargement des articles:', error);
      }
    );
  }

  deleteArticle(articleId: number) {

    const confirmation = confirm('Voulez-vous vraiment supprimer cet article ?');

    // Méthode pour supprimer un article
    if (confirmation) {
      this.http.delete(`http://localhost:8080/deleteArticle/${articleId}`,{ responseType: 'text' }).subscribe(
        () => {
          // Afficher un message de suppression réussie
          this.showSuccessMessage();
  
          setTimeout(() => {
            window.location.reload(); // Rafraîchir la page après 3 secondes
          }, 3000);
        },
        (error) => {
          console.error('Erreur lors de la suppression de l\'article:', error);
        }
      );
    }
  }

  showSuccessMessage(): void {
    this.snackBar.open('Vous avez supprimer un article', 'Fermer', {
      duration: 3000, // Durée d'affichage de l'alerte en millisecondes
    });
  }

  editArticle(article: any): void {
    console.log("Article complet :", article);
    console.log("Catégorie de l'article :", article.catégorie); // Assurez-vous que la propriété est correcte
  
    const dialogRef = this.dialog.open(EditArticleDialogComponent, {
      width: '400px',
      data: { article } // Ici, vous transmettez l'objet entier de l'article
    });
  
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadSellerArticles(this.sellerId!);
      }
    });
  }
}
