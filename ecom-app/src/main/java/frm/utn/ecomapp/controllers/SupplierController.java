package frm.utn.ecomapp.controllers;

import frm.utn.ecomapp.dtos.Supplier;
import frm.utn.ecomapp.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("api/v1/ecom")
public class SupplierController {
@Autowired
SupplierService supplierService;
    @GetMapping("/suppliers")
    public String suppliers(Model model) {
try{
    List<Supplier> supplierList=supplierService.consumeSuppliers();
    model.addAttribute("suppliers", supplierList);
    return "suppliers";
}catch (Exception e){
    return "Errors";
}
    }

}

