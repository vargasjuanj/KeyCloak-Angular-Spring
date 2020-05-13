import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { KeycloakSecurityService } from '../services/keycloak-security.service';

@Injectable({
  providedIn: 'root'
})
export class IsUserGuard implements CanActivate {
  constructor(private keycloakSecurityService:KeycloakSecurityService){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
        
      if (!this.keycloakSecurityService.keycloak.hasRealmRole('user')) {
        console.log('No es user');
        alert("Contacte con el admiistrador para obtener los permisos necesarios de usuario")
        //redirección
        return false;
    }
    
    return true;
  }
  
}
