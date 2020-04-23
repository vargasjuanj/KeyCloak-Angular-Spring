package frm.utn.controllers;




import frm.utn.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import frm.utn.entities.Supplier;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/ecom")
public class SupplierController   {

@Autowired
SupplierService supplierService;
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers( ) throws Exception {
        System.out.println("ADMIN SOLAMENTE");
return  supplierService.findAll();
    }



}
