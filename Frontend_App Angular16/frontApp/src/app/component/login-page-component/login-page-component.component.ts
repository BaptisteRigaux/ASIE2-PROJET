import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SericeAuthService } from 'src/app/service/serice-auth.service';

@Component({
  selector: 'app-login-page-component',
  templateUrl: './login-page-component.component.html',
  styleUrls: ['./login-page-component.component.css']
})
export class LoginPageComponentComponent implements OnInit {

  // Foncionnalité de login form automatique
  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [
      Validators.required,
      Validators.minLength(6),
      Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d{2})[A-Za-z\d@$!%*#?&]{6,}$/) // Ajout du pattern
    ]]
  })

  constructor(private router: Router , private fb: FormBuilder, private SericeAuthService: SericeAuthService ,private snackBar: MatSnackBar ) {}

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 10000, // Durée d'affichage du message en millisecondes
    });
  }

  // Fonctionnalité de renvoie sur le button registrer
  redirectToRegister() {
    console.log('Redirecting to registration page');
    this.router.navigateByUrl('/registrer');
  }

  loginUser() {
    const {email,password} = this.loginForm.value;
    console.log(email,password);
    this.SericeAuthService.loginUser(email as string).subscribe(
      response => {
        if(response && response.password === password){
          sessionStorage.setItem('email', email as string);
          this.router.navigate(['/home']);
        }else {
          this.openSnackBar('Wrong Crédential', 'Close');
        }
      },
      error => {
        this.openSnackBar('Wrong Crédential', 'Close');
      }
    ) }

  // Définition des inputs à utilisé pour les conditions
  get email() { return this.loginForm.controls["email"];}
  get password() { return this.loginForm.controls["password"];}

  ngOnInit() {
    // Vérifie si l'utilisateur est déjà connecté en inspectant le sessionStorage ou le localStorage
    const isLoggedIn = !!sessionStorage.getItem('email'); // Ou tout autre indicateur de connexion

    // Si l'utilisateur est déjà connecté, redirige vers la page d'accueil
    if (isLoggedIn) {
      this.router.navigate(['/home']); // Redirection vers la page d'accueil
    }
  }
}
