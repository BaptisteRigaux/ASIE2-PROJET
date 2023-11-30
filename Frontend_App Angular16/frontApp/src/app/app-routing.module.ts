import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponentComponent } from './component/login-page-component/login-page-component.component';
import { RegistrerPageComponentComponent } from './component/registrer-page-component/registrer-page-component.component';
import { HomePageComponentComponent } from './component/home-page-component/home-page-component.component';
import { UserOrdersComponentComponent } from './component/user-orders-component/user-orders-component.component';
import { PersonalInfosComponentComponent } from './component/personal-infos-component/personal-infos-component.component';

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
    path: "users/:customerId/orders", 
    component: UserOrdersComponentComponent
  },
  {
    path: "users/:userId/personalInfos/:customerId", 
    component: PersonalInfosComponentComponent 
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
