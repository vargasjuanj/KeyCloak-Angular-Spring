package frm.utn.controllers;

import frm.utn.services.SupplierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import frm.utn.entities.Supplier;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/ecom/suppliers")
public class SupplierController extends ControllerGenerico<Supplier, SupplierService> {
    // Nota: Se recomienda extender del Controller Generico, cuando todos los roles
    // que van a usar este servicio van a usar todas las funciones crud, ya que si
    // se restringen unas operaciones y otras no para ciertos roles, no funciona
    // bien..
    // En este caso solo el rol admin va usarlo

       @PreAuthorize("hasAnyRole('ROLE_admin')")
    public ResponseEntity<?> getSuppliers() throws Exception {
        System.out.println("ADMIN SOLAMENTE");
return  this.getAll(); //utiliza el método del generico, ya tiene su path configurado
    }



}
