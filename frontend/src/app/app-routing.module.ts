import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';



import { ProductComponent } from './components/product/product.component';
import { SupplierComponent } from './components/supplier/supplier.component';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './guards/auth.guard';
import { IsAdminGuard } from './guards/is-admin.guard';



const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'suppliers', component:SupplierComponent, canActivate:[AuthGuard, IsAdminGuard]},
  {path:'products',component:ProductComponent, canActivate:[AuthGuard]},
  {path: '**',component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
