import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page-component',
  templateUrl: './login-page-component.component.html',
  styleUrls: ['./login-page-component.component.css']
})
export class LoginPageComponentComponent {

  // Foncionnalité de login form automatique
  loginForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', [
      Validators.required,
      Validators.minLength(6),
      Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d{2})[A-Za-z\d@$!%*#?&]{6,}$/) // Ajout du pattern
    ]]
  })

  constructor(private router: Router , private fb: FormBuilder) {}

  // Fonctionnalité de renvoie sur le button registrer
  redirectToRegister() {
    console.log('Redirecting to registration page');
    this.router.navigateByUrl('/registrer');
  }

  // Définition des inputs à utilisé pour les conditions
  get email() { return this.loginForm.controls["email"];}
  get password() { return this.loginForm.controls["password"];}
}
