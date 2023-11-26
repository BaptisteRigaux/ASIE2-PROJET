import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { passwordMatchValidator } from '../../password-match/password-match.directives';
import { CreateUserServiceService } from 'src/app/service/create-user-service.service';
import { LoginPageComponentComponent } from '../login-page-component/login-page-component.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-registrer-page-component',
  templateUrl: './registrer-page-component.component.html',
  styleUrls: ['./registrer-page-component.component.css']
})
export class RegistrerPageComponentComponent {

  RegistrerForm = this.fb.group({
    Firstname: ['', Validators.required],
    Lastname: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    dateOfBirth: [''],
    gender: ['', Validators.required],
    password: ['', Validators.required],
    confirmPassword: ['',Validators.required],
    
  },{
    validators: passwordMatchValidator
  })

  constructor(private router: Router, private fb: FormBuilder , private CreateUserServiceService: CreateUserServiceService,private snackBar: MatSnackBar) {}

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 4000, // Durée d'affichage du message en millisecondes
    });
  }

  redirectToLogin() {
    console.log('Redirecting to registration page');
    this.router.navigateByUrl('/login');
  }

  submitUserDetails() {
    if (this.RegistrerForm.valid) {
      const currentDate = new Date().toISOString(); // Récupération de la date actuelle
  
      // Exclure confirmPassword et ajouter la date actuelle à la validation ( formulaire de données envoyé au service ) 
      const { confirmPassword, ...userData } = this.RegistrerForm.value;
      const userDataWithRegistrationDate = { ...userData,registrationDate: currentDate};
      console.log(userDataWithRegistrationDate);

      // Envoi des données à travers le service Angular
      this.CreateUserServiceService.addUser(userDataWithRegistrationDate).subscribe(
        (response) => {
          console.log('User added successfully:', response);
          this.openSnackBar('Registration successful', 'Close');
          this.router.navigate([LoginPageComponentComponent])
        },
        (error) => {
          console.error('Error adding user:', error);
          this.openSnackBar('Bad Registration', 'Close');
        }
      );
    } else {
      console.log('Invalid form. Please check the fields.');
      // Gérer le cas où le formulaire est invalide
      this.openSnackBar('Bad Registration', 'Close');
    }
    console.log(this.RegistrerForm.value);
  }

  // Définition des inputs à utilisé pour les conditions
  get email() { return this.RegistrerForm.controls["email"];}
  get password() { return this.RegistrerForm.controls["password"];}
  get confirmPassword() { return this.RegistrerForm.controls["confirmPassword"];}

}
