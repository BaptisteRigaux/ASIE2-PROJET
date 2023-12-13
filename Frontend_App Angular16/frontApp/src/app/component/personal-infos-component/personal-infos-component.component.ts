import {Component , OnInit } from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SericeAuthService } from 'src/app/service/serice-auth.service';
import { AddAddressComponentComponent } from '../add-address-component/add-address-component.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-personal-infos-component',
  templateUrl: './personal-infos-component.component.html',
  styleUrls: ['./personal-infos-component.component.css']
})
export class PersonalInfosComponentComponent implements OnInit{
  panelOpenState = false;
  hide = true;

  userEmail: string | null = null; // Variable pour stocker l'email de l'utilisateur
  userForm!: FormGroup;
  userData: any; // Les données de l'utilisateur
  customerId!: string; // Variable pour stocker customerId
  dialogRef!: MatDialogRef<AddAddressComponentComponent>;
  
  constructor(private formBuilder: FormBuilder,private SericeAuthService: SericeAuthService ,private dialog: MatDialog , private route: ActivatedRoute) {}


  ngOnInit(): void {

    this.userEmail = sessionStorage.getItem('email');
    const userEmail = sessionStorage.getItem('email'); // Récupérer l'email de la session

    // Initialisez le formulaire avec les données utilisateur (placeholders)
    this.userForm = this.formBuilder.group({
      Firstname: ['', Validators.required],
      Lastname: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      dateOfBirth: ['', Validators.required],
      password: ['', Validators.required],
      gender: ['', Validators.required] // Ajoute le champ pour la civilité
    });

    this.SericeAuthService.loginUser(userEmail as string).subscribe(
      (data: any) => {
        this.userData = data;

        // Remplissez le formulaire avec les données utilisateur
        this.userForm.patchValue({
          Firstname: this.userData.Firstname,
          Lastname: this.userData.Lastname,
          email: this.userData.email,
          dateOfBirth: this.userData.dateOfBirth,
          password: this.userData.password,
          gender: this.userData.gender,
        });
      },
      (error) => {
        console.error('Erreur lors de la récupération des données utilisateur : ', error);
      });

      //Récupérer le customerId depuis la route
      this.route.params.subscribe((params) => {
        this.customerId = params['customerId']; // Obtenez le customerId de l'URL
        console.log(this.customerId);
      });

    }



  onSubmit(): void {
    if (this.userForm.valid) {
      // Soumettre les modifications au service pour mise à jour de l'utilisateur
      const userId = this.userData.user_id;
      // Ajoute l'ID de l'utilisateur aux données à mettre à jour
        const updatedUserData = {
          user_id: userId,
          ...this.userForm.value
        };

      this.SericeAuthService.updateUserData(updatedUserData).subscribe(
      (response) => {
        console.log('Informations utilisateur mises à jour avec succès : ', response);
        window.location.reload();
      },
      (error) => {
        console.error('Erreur lors de la mise à jour des informations utilisateur : ', error);
      }
    );
  } else {
    console.error('Veuillez remplir correctement tous les champs du formulaire.');
  }
  }

  openAddAddressModal(): void {
    this.dialogRef = this.dialog.open(AddAddressComponentComponent, {
      width: '400px', // Largeur du modal (ajustez selon vos besoins)
      data: { customerId: this.customerId} // Passer le customerId au dialogue
    });

  
    // Souscrire à l'événement d'ajout d'adresse réussi depuis AddAddressComponentComponent
    this.dialogRef.componentInstance.addAddressSuccess.subscribe((result: string) => {
      if (result === 'success') {
        console.log('Dialogue fermé suite à un ajout d\'adresse réussi.');
        this.dialogRef.close(); // Fermer le dialogue ici
        window.location.reload(); // Rafraîchir la page
      }
    });
  }
}
