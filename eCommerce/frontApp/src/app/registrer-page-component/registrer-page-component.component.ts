import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrer-page-component',
  templateUrl: './registrer-page-component.component.html',
  styleUrls: ['./registrer-page-component.component.css']
})
export class RegistrerPageComponentComponent {
  constructor(private router: Router) {}

  redirectToLogin() {
    console.log('Redirecting to registration page');
    this.router.navigateByUrl('/login');
  }

}
