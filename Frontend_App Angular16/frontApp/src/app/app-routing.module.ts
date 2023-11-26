import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponentComponent } from './component/login-page-component/login-page-component.component';
import { RegistrerPageComponentComponent } from './component/registrer-page-component/registrer-page-component.component';
import { HomePageComponentComponent } from './component/home-page-component/home-page-component.component';
import { UserOrdersComponentComponent } from './component/user-orders-component/user-orders-component.component';

const routes: Routes = [
  {
    path: "login",
    component: LoginPageComponentComponent
  },
  {
    path: "registrer",
    component: RegistrerPageComponentComponent
  },
  {
    path: "home",
    component: HomePageComponentComponent
  },
  {
    path: "users/:userId/orders", 
    component: UserOrdersComponentComponent
  },
  {
    path: "",
    redirectTo: 'home',
    pathMatch: 'full',
  },
  { path: '**', redirectTo: '/home' }, // Redirige toutes les routes inconnues vers le login
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
