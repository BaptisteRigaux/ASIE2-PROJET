import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../component/user-orders-component/user-orders-component.component';

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getOrdersByCustomerId(customerId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/users/${customerId}/orders`);
  }

  getAllArticles():Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/allArticles`)
  }

  addToPanier(panierId: number | null |undefined, userId:number, article :Article):Observable<any> {
    console.log(panierId)
    if(panierId !== null && panierId !== undefined){
      const url = `${this.apiUrl}/addToPanier/${panierId}/${userId}`;
      return this.http.post<any>(url,article);
    }else {
      const url = `${this.apiUrl}/addToPanier/null/${userId}`
      return this.http.post<any>(url,article);
    }
  }

  getPanier(panierId: number):Observable<any>{
    const url = `${this.apiUrl}/order/${panierId}`;
    return this.http.get<any>(url)
    
  };

  supprimerArticleDuPanier(panierId: number, articleId: number) {
    return this.http.delete(`${this.apiUrl}/panier/${panierId}/article/${articleId}`);
  }

}
