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
import {BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar'; // Importez le module MatSnackBarModule

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponentComponent,
    HomePageComponentComponent,
    RegistrerPageComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
  ],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
