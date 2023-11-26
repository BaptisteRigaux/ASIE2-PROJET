import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { passwordMatchValidator } from '../../password-match/password-match.directives';
import { CreateUserServiceService } from 'src/app/service/create-user-service.service';

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

  constructor(private router: Router, private fb: FormBuilder , private CreateUserServiceService: CreateUserServiceService) {}

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
          // Redirection vers une page de confirmation ou autre action
        },
        (error) => {
          console.error('Error adding user:', error);
          // Gérer l'erreur appropriée (afficher un message à l'utilisateur, par exemple)
        }
      );
    } else {
      console.log('Invalid form. Please check the fields.');
      // Gérer le cas où le formulaire est invalide
    }
    console.log(this.RegistrerForm.value);
  }

  // Définition des inputs à utilisé pour les conditions
  get email() { return this.RegistrerForm.controls["email"];}
  get password() { return this.RegistrerForm.controls["password"];}
  get confirmPassword() { return this.RegistrerForm.controls["confirmPassword"];}

}
