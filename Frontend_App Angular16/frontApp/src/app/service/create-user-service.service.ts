import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateUserServiceService {


  private apiUrl = 'http://localhost:8080'; // Remplace par l'URL de ton API

  constructor(private http: HttpClient) { }

  addUser(user: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/user/add`, user);
  }
}
