import { Component, EventEmitter, Inject, OnInit, Output, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-address-component',
  templateUrl: './add-address-component.component.html',
  styleUrls: ['./add-address-component.component.css']
})
export class AddAddressComponentComponent {
  @Output() addAddressSuccess = new EventEmitter<string>();
  @Output() addAddress = new EventEmitter<any>(); // Événement pour émettre l'adresse ajoutée
  addressForm: FormGroup; // Formulaire pour ajouter une adresse

  customerId!: string; // Variable pour stocker customerId

  countries = [
    { value: 'fr', viewValue: 'France' },
    { value: 'us', viewValue: 'United States' },
    // ... Autres pays que vous souhaitez ajouter
  ];

  constructor(private formBuilder: FormBuilder, private dialog: MatDialog,  
    public dialogRef: MatDialogRef<AddAddressComponentComponent>, private http: HttpClient ,@Inject(MAT_DIALOG_DATA) public data: any) {

    // Défini le formulaire   
    this.addressForm = this.formBuilder.group({
      street: ['', Validators.required],
      postal_code: ['', Validators.compose([Validators.required, Validators.pattern('^[0-9]*$')])],
      city: ['', Validators.required],
      country: ['', Validators.required]

    });

    if (data && data.customerId) {
      this.customerId = data.customerId;
      console.log(this.customerId); // Vérifier si le customerId est correctement reçu
    }
  }

  // Méthode appelée lors de la soumission du formulaire
  onSubmit(): void {
    if (this.addressForm.valid) {
      const addressData = this.addressForm.value;

      this.addAddressToCustomer(addressData); // Appel de la méthode pour ajouter l'adresse
      this.addressForm.reset(); // Réinitialisation du formulaire après l'envoi
    }
  }

   // Méthode pour ajouter une adresse via l'API Spring Boot
   addAddressToCustomer(addressData: any) {
    const apiUrl = `http://localhost:8080/addAdress/${this.customerId}`; // Remplacez par votre URL API
    
    // Utilisez HttpClient pour envoyer les données au backend
    this.http.post(apiUrl, addressData ,  { responseType: 'text' }).subscribe(
      (response) => {
        console.log('Address added successfully:', response);
        this.addAddressSuccess.emit('success'); // Émettre un événement de succès
        // Traitez la réponse ou mettez à jour l'interface utilisateur si nécessaire
      },
      (error) => {
        console.error('Error adding address:', error);
        // Gérez les erreurs et informez l'utilisateur si nécessaire
      }
    );
  }


}
