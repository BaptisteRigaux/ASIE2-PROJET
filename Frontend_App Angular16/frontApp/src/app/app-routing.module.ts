import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponentComponent } from './component/login-page-component/login-page-component.component';
import { RegistrerPageComponentComponent } from './component/registrer-page-component/registrer-page-component.component';
import { HomePageComponentComponent } from './component/home-page-component/home-page-component.component';
import { UserOrdersComponentComponent } from './component/user-orders-component/user-orders-component.component';
import { PersonalInfosComponentComponent } from './component/personal-infos-component/personal-infos-component.component';
import { AddAddressComponentComponent } from './component/add-address-component/add-address-component.component';
import { AddArticleComponent } from './component/add-article/add-article.component';
import { ManageSellerComponent } from './component/manage-seller/manage-seller.component';

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
    component: UserOrdersComponentComponent ,
  },
  {
    path: "users/:userId/personalInfos/:customerId", 
    component: PersonalInfosComponentComponent , 
  },
  {
    path: "manageSeller/:sellerId", 
    component: ManageSellerComponent,
  },
  {
    path: "AddArticle/:userId/:sellerId", 
    component: AddArticleComponent , 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
