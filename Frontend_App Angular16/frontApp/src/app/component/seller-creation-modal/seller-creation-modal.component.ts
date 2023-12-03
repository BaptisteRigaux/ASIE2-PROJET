import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';

interface SellerCreationResponse {
  seller_id: number;
  name_seller: string;
  email_seller: string;
  phone_seller: number;
  company_ID: number;
  adress_seller: string;
  articles: any[]; // Remplacez `any[]` par le type approprié pour le tableau d'articles
}

@Component({
  selector: 'app-seller-creation-modal',
  templateUrl: './seller-creation-modal.component.html',
  styleUrls: ['./seller-creation-modal.component.css']
})
export class SellerCreationModalComponent {
    sellerForm: FormGroup;
    userId!: number; // Variable pour stocker customerId

    constructor(
      private fb: FormBuilder,
      public dialogRef: MatDialogRef<SellerCreationModalComponent>,
      private http: HttpClient,
      @Inject(MAT_DIALOG_DATA) public data: any
    ) {

    this.sellerForm = this.fb.group({
      company_ID: ['', Validators.required],
      email_seller: ['', [Validators.required, Validators.email]],
      name_seller: ['', Validators.required],
      phone_seller: ['', Validators.required],
      adress_seller: ['', Validators.required]
    });

    if (data && data.userId) {
      this.userId = data.userId;
      console.log(this.userId); // Vérifier si le customerId est correctement reçu
    }
  }


  // Méthode appelée lors de la soumission du formulaire
  createSeller(): void {
    if (this.sellerForm.valid) {
      const sellerData = this.sellerForm.value;

      this.addSellerToUser(sellerData); // Appel de la méthode pour ajouter l'adresse
    }
  }
  addSellerToUser(sellerData: any): void {
    console.log(sellerData);
    const apiUrl = `http://localhost:8080/addSeller/${this.userId}`; // Remplacez par votre URL API
    
    // Utilisez HttpClient pour envoyer les données au backend
    this.http.post<SellerCreationResponse>(apiUrl, sellerData ).subscribe(
      (response) => {
        const newSellerId = response.seller_id; // Remplacez `id` par le champ contenant l'ID du vendeur dans la réponse

        console.log('Address added successfully:', response);
        this.dialogRef.close({ action: 'sellerCreated', sellerId: newSellerId });
        // Traitez la réponse ou mettez à jour l'interface utilisateur si nécessaire
      },
      (error) => {
        console.error('Error adding address:', error);
        // Gérez les erreurs et informez l'utilisateur si nécessaire
      }
    );
  }
  onCloseClick(): void {
    this.dialogRef.close();
  }

}
