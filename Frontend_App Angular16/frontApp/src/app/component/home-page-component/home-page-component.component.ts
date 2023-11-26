import { Component , OnInit} from '@angular/core';

@Component({
  selector: 'app-home-page-component',
  templateUrl: './home-page-component.component.html',
  styleUrls: ['./home-page-component.component.css']
})
export class HomePageComponentComponent implements OnInit{
  
  userEmail: string | null = null; // Variable pour stocker l'email de l'utilisateur

  ngOnInit() {
    // Récupérer l'email utilisateur à partir du sessionStorage
    this.userEmail = sessionStorage.getItem('email');
  }
}
