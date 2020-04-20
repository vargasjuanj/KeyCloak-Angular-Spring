package frm.utn.suplierservice.controllers;


import frm.utn.suplierservice.entities.Supplier;
import frm.utn.suplierservice.services.SupplierService;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Con @Controller no me muestra el string que retorna supplliers() pero con @RestController no me retorna las plantillas html
@RequestMapping("api/v1/ecom")
public class SupplierController extends ControllerGenerico<Supplier, SupplierService> {

    @GetMapping("/suppliers")
    public List<Supplier> suppliers() throws Exception {
return service.findAll();

    }






}