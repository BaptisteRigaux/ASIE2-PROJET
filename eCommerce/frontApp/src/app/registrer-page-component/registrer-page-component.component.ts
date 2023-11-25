import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { passwordMatchValidator } from '../password-match/password-match.directives';

@Component({
  selector: 'app-registrer-page-component',
  templateUrl: './registrer-page-component.component.html',
  styleUrls: ['./registrer-page-component.component.css']
})
export class RegistrerPageComponentComponent {

  RegistrerForm = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    confirmPassword: ['',Validators.required]
  },{
    validators: passwordMatchValidator
  })

  constructor(private router: Router, private fb: FormBuilder) {}

  redirectToLogin() {
    console.log('Redirecting to registration page');
    this.router.navigateByUrl('/login');
  }

  // Définition des inputs à utilisé pour les conditions
  get email() { return this.RegistrerForm.controls["email"];}
  get password() { return this.RegistrerForm.controls["password"];}
  get confirmPassword() { return this.RegistrerForm.controls["confirmPassword"];}

}
