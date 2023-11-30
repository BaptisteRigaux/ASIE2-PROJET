import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SericeAuthService } from 'src/app/service/serice-auth.service';

@Component({
  selector: 'app-address-details',
  templateUrl: './address-details.component.html',
  styleUrls: ['./address-details.component.css']
})
export class AddressDetailsComponent implements OnInit{
  customerId: string | null = null;
  addresses: any[] = [];

  constructor(private route: ActivatedRoute, private sericeAuth: SericeAuthService) { }

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

  //action sur le click supprimer des adress
  onDeleteIconClick(address: any): void {
    console.log('Test suppression', address);
    // Ajoutez ici la logique pour supprimer l'adresse (par exemple, en utilisant un service)
  }
}
