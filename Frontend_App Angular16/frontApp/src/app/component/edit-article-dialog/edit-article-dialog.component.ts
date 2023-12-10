import { HttpClient } from '@angular/common/http';
import { Component , Inject  } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-article-dialog',
  templateUrl: './edit-article-dialog.component.html',
  styleUrls: ['./edit-article-dialog.component.css']
})
export class EditArticleDialogComponent {

  articleData: any; // Stockez les données de l'article ici pour l'édition
  selectedArticle: any; // Déclaration de la propriété selectedArticle


  constructor(
    public dialogRef: MatDialogRef<EditArticleDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private http: HttpClient,
  ) {

    this.selectedArticle = data.article;
    console.log(data)
    console.log(data.article.name_article)
  }

  /* Méthode pour sauvegarder les modifications de l'article */
  saveChanges(): void {
    // Ici, vous pouvez utiliser this.selectedArticle pour envoyer les données à votre API
    this.http.put(`http://localhost:8080/api/updateArticle/${this.selectedArticle.article_id}`, this.selectedArticle)
      .subscribe(
        (response) => {
          console.log('Article mis à jour avec succès:', response);
          this.dialogRef.close(true);
        },
        (error) => {
          console.error('Erreur lors de la mise à jour de l\'article:', error);
          // Gérez les erreurs ici
        }
      );
  }

  /* Méthode pour annuler l'édition et fermer le dialog */
  cancel(): void {
    this.dialogRef.close();
  }
}
