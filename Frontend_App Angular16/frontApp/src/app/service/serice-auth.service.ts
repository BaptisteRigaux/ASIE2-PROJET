import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SericeAuthService {

  private apiUrl = 'http://localhost:8080'; // Remplace par l'URL de ton API

  constructor(private http: HttpClient) { }

  loginUser(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/user/email=${email}`);
  }
}