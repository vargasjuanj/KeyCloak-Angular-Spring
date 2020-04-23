import { Component, OnInit } from '@angular/core';
import { KeycloakSecurityService } from 'src/app/services/keycloak-security.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

title = 'keycloak-app';
  constructor(private keycloakSecurityService: KeycloakSecurityService) { }
  isAuth = false;
  keycloak: any;
  userInformations: any;
  ngOnInit() {
    this.keycloak = this.keycloakSecurityService.keycloak
    console.log(this.keycloak);
    // console.log('hasRealmRole', this.keycloak.hasRealmRole('admin'));//
    //console.log('hasResourceRole', this.keycloak.hasResourceRole('admin'));//
    this.isAuth = this.keycloak.authenticated;
    this.userInformations = this.isAuth ? this.keycloak.idTokenParsed : {};
  }

  onLogin() {
    this.keycloak.login();
  }
  onLogout() {
    this.keycloak.logout();
  }
  ManagedAccount() {
    this.keycloak.accountManagement();
  }
  isAdmin() {
    return this.keycloak.hasRealmRole('admin')
  }
}
