import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponentComponent } from './login-page-component/login-page-component.component';
import { HomePageComponentComponent } from './home-page-component/home-page-component.component';
import { RegistrerPageComponentComponent } from './registrer-page-component/registrer-page-component.component';
import { ReactiveFormsModule } from '@angular/forms';

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
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
