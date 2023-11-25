import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponentComponent } from './component/login-page-component/login-page-component.component';
import { HomePageComponentComponent } from './component/home-page-component/home-page-component.component';
import { RegistrerPageComponentComponent } from './component/registrer-page-component/registrer-page-component.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
