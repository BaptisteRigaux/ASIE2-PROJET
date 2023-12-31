import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { OrderServiceService } from "src/app/service/order-service.service";


interface Order {
  order_id: number;
  status_order: string;
  date_order: string;
  number_order: number;
  articlePriceHistories: ArticlePriceHistory[]; // Vous pouvez définir ici la structure appropriée pour les articles si nécessaire
  total_amount: number;
}

export interface ArticlePriceHistory {
  articleprice_history_id: number;
  price_article: number;
  price_start: string;
  date_end: string;
  article: Article;
}

export interface Article {
  article_id: number;
  name_article: string;
  category: string;
  description: string;
  stock: number;
  price: number;
}


@Component({
  selector: 'app-user-orders-component',
  templateUrl: './user-orders-component.component.html',
  styleUrls: ['./user-orders-component.component.css']
})

export class UserOrdersComponentComponent implements OnInit {

  displayedColumns: string[] = ['itemName','price'];
  
  constructor(private orderService: OrderServiceService, private route: ActivatedRoute) { }

  userOrders: Order[] = [];
  
  ngOnInit(): void {
    // Récupérer l'ID de l'utilisateur dans l'URL
    const customerId = this.route.snapshot.paramMap.get('customerId'); // Supposons que l'ID est dans la route
    
    if (customerId !== null) {
      // Appel du service pour récupérer les commandes de l'utilisateur spécifié
      this.orderService.getOrdersByCustomerId(parseInt(customerId)).subscribe((data) => {
        // Mettre à jour les commandes de l'utilisateur
        this.userOrders = data;
        console.log(this.userOrders);
      });
    } else {
      // Gérer le cas où l'ID n'est pas disponible dans l'URL
      console.error('Customer ID not found in URL');
    }
  }
}