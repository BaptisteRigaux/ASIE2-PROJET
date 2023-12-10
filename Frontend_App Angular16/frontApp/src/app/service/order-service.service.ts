import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../component/user-orders-component/user-orders-component.component';


@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  onArticleAddedToPanier = new EventEmitter<void>();
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
    const url = panierId !== null && panierId !== undefined 
                ? `${this.apiUrl}/addToPanier/${panierId}/${userId}` 
                : `${this.apiUrl}/addToPanier/null/${userId}`;

    console.log(`Sending request to: ${url} with article:`, article);
    return this.http.post(url, article, { responseType: 'text' });
    this.onArticleAddedToPanier.emit(); // Émettre un événement après l'ajout réussi
  }

  getPanier(panierId: number):Observable<any>{
    const url = `${this.apiUrl}/order/${panierId}`;
    return this.http.get<any>(url)
    
  };

  supprimerArticleDuPanier(panierId: number, articleId: number) {
    return this.http.delete(`${this.apiUrl}/panier/${panierId}/article/${articleId}`);
  }

}
