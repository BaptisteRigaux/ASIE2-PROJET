import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient) {}

  getOrdersByCustomerId(customerId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${customerId}/orders`);
  }

}
