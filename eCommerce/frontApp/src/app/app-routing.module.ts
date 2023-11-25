import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponentComponent } from './component/login-page-component/login-page-component.component';
import { RegistrerPageComponentComponent } from './component/registrer-page-component/registrer-page-component.component';
import { HomePageComponentComponent } from './component/home-page-component/home-page-component.component';

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
    path: "",
    redirectTo: 'login',
    pathMatch: 'full',
  },
  { path: '**', redirectTo: '/login' }, // Redirige toutes les routes inconnues vers le login

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
