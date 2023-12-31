import { NgModule } from '@angular/core';
import { BrowserModule, platformBrowser } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponentComponent } from './component/login-page-component/login-page-component.component';
import { HomePageComponentComponent } from './component/home-page-component/home-page-component.component';
import { RegistrerPageComponentComponent } from './component/registrer-page-component/registrer-page-component.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavBarComponentComponent } from './component/nav-bar-component/nav-bar-component.component'; // Importez le module MatSnackBarModule

import { UserOrdersComponentComponent } from './component/user-orders-component/user-orders-component.component';
import { PersonalInfosComponentComponent } from './component/personal-infos-component/personal-infos-component.component';

import { MatExpansionModule} from '@angular/material/expansion';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { AddressDetailsComponent } from './component/address-details/address-details.component';
import { OrdersCardsComponent } from './component/orders-cards/orders-cards.component';
import { AddAddressComponentComponent } from './component/add-address-component/add-address-component.component';
import { MatDialogModule } from '@angular/material/dialog';
import { AddArticleComponent } from './component/add-article/add-article.component';
import { SellerCreationModalComponent } from './component/seller-creation-modal/seller-creation-modal.component';
import { ManageSellerComponent } from './component/manage-seller/manage-seller.component';
import { EditArticleDialogComponent } from './component/edit-article-dialog/edit-article-dialog.component';
import { FormsModule } from '@angular/forms'; // Importez FormsModule
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatBadgeModule } from '@angular/material/badge';
import { CommandpageComponent } from './component/commandpage/commandpage.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponentComponent,
    HomePageComponentComponent,
    RegistrerPageComponentComponent,
    NavBarComponentComponent,
    UserOrdersComponentComponent,
    PersonalInfosComponentComponent,
    AddressDetailsComponent,
    OrdersCardsComponent,
    AddAddressComponentComponent,
    AddArticleComponent,
    SellerCreationModalComponent,
    ManageSellerComponent,
    EditArticleDialogComponent,
    CommandpageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatToolbarModule, // Importez MatToolbarModule
    MatMenuModule,    // Importez MatMenuModule
    MatIconModule,    // Importez MatIconModule
    MatButtonModule,  // Importez MatButtonModule
    MatExpansionModule,
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRadioModule,
    MatSelectModule,
    MatCardModule,
    MatTableModule,
    MatDialogModule,
    FormsModule,
    MatTooltipModule,
    MatBadgeModule,  
    MatSidenavModule,  
    MatListModule,
   ],

  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
