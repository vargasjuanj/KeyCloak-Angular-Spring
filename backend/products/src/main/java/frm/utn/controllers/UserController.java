package frm.utn.controllers;


import javax.servlet.http.HttpServletRequest;


import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import org.keycloak.representations.AccessToken;

import org.keycloak.representations.AccessToken.Access;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;






@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/ecom/currentUser")
public class UserController {



    @GetMapping("/")
    public String  getAll(HttpServletRequest request ){
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) request.getUserPrincipal();        
        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String username = accessToken.getPreferredUsername();
        String userID= accessToken.getId();
        String emailID = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();   
        String clienteID= accessToken.issuedFor;         
       Access realmAccess = accessToken.getRealmAccess();
      //String  direccion = accessToken.getAddress().toString();
//accessToken.setBirthdate("BirthdayTest");   //El tema de agregar un usuario con propiedades extras de forma automatica es para investigra, de todas formas con los datos que ya brinda seria suficiente, lo demas se deberian agregar de otra forma que no sea  a traves de keyycloak de forma manual
//String birthday= accessToken.getBirthdate();
    //accessToken.setEmail("Picapedro@gmail.co");
       String gender= accessToken.getGender();  //Atributo agregado de forma manual para prueba. Por defecto vienen los metodos get y set de algunos atributos, pero estos otros atributos secundarios como el genero deben crearse en la pestaña attributos de los usuarios.
   

return username+" "+emailID+" "+lastname+" "+firstname+" "+realmName+" "+gender+" "+clienteID+" "+userID;

}
    

}
