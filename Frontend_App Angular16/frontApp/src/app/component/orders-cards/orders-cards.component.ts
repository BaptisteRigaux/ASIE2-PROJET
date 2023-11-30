import { Component, OnInit } from '@angular/core';
import { OrderServiceService } from "src/app/service/order-service.service";

@Component({
  selector: 'app-orders-cards',
  templateUrl: './orders-cards.component.html',
  styleUrls: ['./orders-cards.component.css']
})
export class OrdersCardsComponent implements OnInit{
  articles: any;

  constructor(private orderService: OrderServiceService) { }

  ngOnInit(): void {
    this.orderService.getAllArticles().subscribe(
      (data: any[]) => {
        this.articles = data; // Assigner les données d'articles au tableau d'articles
        console.log(this.articles);
      },
      (error) => {
        console.error('Une erreur est survenue lors de la récupération des articles : ', error);
      }
    );
  }


}
