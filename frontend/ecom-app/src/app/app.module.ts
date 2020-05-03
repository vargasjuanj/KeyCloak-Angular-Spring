import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { ReactiveFormsModule } from '@angular/forms';
import { TokenInterceptor } from './services/token-interceptor';
import { NavbarComponent } from './components/navbar/navbar.component';

import { FormComponent } from './components/form/form.component';
import { SupplierService } from './services/supplier.service';
import { ProductService } from './services/product.service';
import { KeycloakSecurityService } from './services/keycloak-security.service';
import { HomeComponent } from './components/home/home.component';

import { ProductComponent } from './components/product/product.component';
import { SupplierComponent } from './components/supplier/supplier.component';
import { ClientComponent } from './components/client/client.component';
import { ClientService } from './services/Client.service';





 
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProductComponent,
SupplierComponent,
    HomeComponent,
    FormComponent,
    ClientComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    SupplierService,
    ProductService,
    ClientService,
    KeycloakSecurityService,
    {provide: APP_INITIALIZER, useFactory: kcFactory, deps: [KeycloakSecurityService], multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
export function kcFactory(keycloakService: KeycloakSecurityService) {
  return () => keycloakService.init();
}
