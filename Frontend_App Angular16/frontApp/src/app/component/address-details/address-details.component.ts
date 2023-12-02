import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SericeAuthService } from 'src/app/service/serice-auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent implements OnInit{
  customerId: string | null = null;
  addresses: any[] = [];

  constructor(private route: ActivatedRoute, private sericeAuth: SericeAuthService , private snackBar: MatSnackBar ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.customerId = params.get('customerId');
      if (this.customerId) {
        this.fetchAddresses(this.customerId);
      }
    });
  }

  fetchAddresses(customerId: string): void {
    this.sericeAuth.getAddressesByCustomerId(customerId)
      .subscribe((data: any[]) => {
        this.addresses = data;
      });
  }

  onDeleteIconClick(address: any): void {
    const confirmation = confirm('Êtes-vous sûr de vouloir supprimer cette adresse ?');

    if (confirmation) {

      console.log('Test suppression', address);
      this.sericeAuth.deleteAddressById(address.adress_id)
        .subscribe(
          (response) => {
            console.log('Suppression OK', response);
            window.location.reload();
            this.snackBar.open('Adresse supprimée avec succès.', 'Fermer', {
              duration: 5000 // Affiche un message de confirmation pendant 2 secondes
            });
            // Ajoutez ici d'autres actions à effectuer après la suppression réussie
          },
          (error) => {
            console.error('Erreur lors de la suppression de l\'adresse', error);
            // Gérez ici les erreurs lors de la suppression
          }
        );
      }
  }
}
