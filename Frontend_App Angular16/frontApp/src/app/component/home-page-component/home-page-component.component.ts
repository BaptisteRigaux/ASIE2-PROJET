import { Component , OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NavBarComponentComponent } from '../nav-bar-component/nav-bar-component.component';

@Component({
  selector: 'app-home-page-component',
  templateUrl: './home-page-component.component.html',
  styleUrls: ['./home-page-component.component.css']
})
export class HomePageComponentComponent implements OnInit{
  
  userEmail: string | null = null; // Variable pour stocker l'email de l'utilisateur

  userData: any; // Variable pour stocker les données utilisateur
  
  constructor(private http: HttpClient ) { }

  ngOnInit() {
    // Récupérer l'email utilisateur à partir du sessionStorage
    this.userEmail = sessionStorage.getItem('email');

    const userEmail = sessionStorage.getItem('email'); // Récupérer l'email de la session

    if (userEmail) {
      this.http.get(`http://localhost:8080/user/email=${userEmail}`).subscribe(
        (data: any) => {
          this.userData = data;
        },
        (error) => {
          console.error('Erreur lors de la récupération des données utilisateur : ', error);
        }
      );
    }
  }
}
