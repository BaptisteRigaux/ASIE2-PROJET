import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SericeAuthService } from 'src/app/service/serice-auth.service';
import { ActivatedRoute , Router  } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { SellerCreationModalComponent } from 'src/app/component/seller-creation-modal/seller-creation-modal.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-add-article',
  templateUrl: './add-article.component.html',
  styleUrls: ['./add-article.component.css']
})
export class AddArticleComponent {

  sellForm: FormGroup;
  sellerId: number | null = null; // Assuming the seller ID is passed in the URL
  userId!: number | null ;
  snackBar: any;

  constructor(private fb: FormBuilder, private authService: SericeAuthService ,private http: HttpClient,
    private route: ActivatedRoute ,  private dialog: MatDialog , private router: Router) {
    this.sellForm = this.fb.group({
      catégory: ['', Validators.required],
      name_article: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      stock: ['', [Validators.required, Validators.min(0)]]
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const sellerIdParam = params.get('sellerId');
      const userIdParam = params.get('userId');

      console.log('sellerIdParam:', sellerIdParam);
      console.log('userIdParam:', userIdParam);

      this.sellerId = sellerIdParam !== null ? parseInt(sellerIdParam, 10) : null;
      if (this.sellerId !== null && Number.isNaN(this.sellerId)) {
        this.sellerId = null;
      }

      this.userId = userIdParam !== null ? +userIdParam : null;

      console.log('Parsed sellerId:', this.sellerId);
      console.log('Parsed userId:', this.userId);
    });
  }

  sellItem(): void {

    const apiUrl = `http://localhost:8080/addArticle/${this.sellerId}`; // Remplacez par votre URL API
    console.log(this.sellForm);
    console.log(this.sellerId);

    if (this.sellForm.valid && this.sellerId) {
      const sellData = this.sellForm.value;

      // Send data to your REST API to create an article for the seller
      this.http.post<any>(apiUrl, sellData)
        .subscribe(
          response => {
            console.log('Article added successfully:', response);
            // Reset the form after successful submission if needed
            this.sellForm.reset();
          },
          error => {
            console.error('Error occurred while adding the article:', error);
            // Handle error messages or actions as needed
          }
        );
    } 
    else if (this.sellerId === null){
        this.openSellerCreationModal();
    }
  }


  openSellerCreationModal(): void {
    console.log(" test " + this.userId)
    const dialogRef = this.dialog.open(SellerCreationModalComponent, {
      width: '400px', // Spécifiez la largeur du modal selon vos besoins
      // Autres configurations de modal
      data: { userId: this.userId } // Transmettez les données nécessaires au modal, telles que l'userId
    });

    dialogRef.afterClosed().subscribe(result => {

      console.log('Modal closed with result:', result);
      if (result.action === 'sellerCreated') {
          this.router.navigate(['/AddArticle' , this.userId ,  result.sellerId ]);
      }
      else {
          // do something here if nothing happend from created seller
      } 
    });
  }
}