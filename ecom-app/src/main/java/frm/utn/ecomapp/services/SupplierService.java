package frm.utn.ecomapp.services;
import frm.utn.ecomapp.dtos.Supplier;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService  {
    @Autowired
    private KeycloakRestTemplate keycloakRestTemplate;
 public List<Supplier> consumeSuppliers() throws Exception {

     //Para una estructura compleja
     try {
         ResponseEntity<List<Supplier>> responseEntity =
                 keycloakRestTemplate.exchange("http://localhost:8083/api/v1/ecom/suppliers",
                         HttpMethod.GET, null, new ParameterizedTypeReference<List<Supplier>>() {
                         });
         List<Supplier> suppliersList = responseEntity.getBody();

     return suppliersList;
     }catch(Exception e){
         throw new Exception(e.getMessage());
     }


 }
//Para una estructura simple . Mas info: https://stackoverflow.com/questions/49752137/how-to-get-list-from-object-in-spring-resttemplate
       /*
        ResponseEntity<Supplier[]>  response = keycloakRestTemplate.getForEntity(
                "http://localhost:8083/suppliers", Supplier[].class);

        List<Supplier> suppliersList = Arrays.asList(response.getBody());

*/



}
