import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';


import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { TokenInterceptor } from './services/token-interceptor';
import { NavbarComponent } from './components/navbar/navbar.component';


import { SupplierService } from './services/supplier.service';
import { ProductService } from './services/product.service';
import { KeycloakSecurityService } from './services/keycloak-security.service';
import { HomeComponent } from './components/home/home.component';

import { ProductComponent } from './components/product/product.component';
import { SupplierComponent } from './components/supplier/supplier.component';




 
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProductComponent,
SupplierComponent,
    HomeComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    SupplierService,
    ProductService,
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
