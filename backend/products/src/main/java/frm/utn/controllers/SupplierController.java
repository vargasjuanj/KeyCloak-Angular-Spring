package frm.utn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import frm.utn.services.SupplierService;

import java.util.List;

import javax.transaction.Transactional;

@CrossOrigin("*")
@Controller
@RequestMapping("api/v1/ecom/suppliers")
public class SupplierController  {
@Autowired
SupplierService supplierService;


@GetMapping("/")
@PreAuthorize("hasAnyRole('ROLE_admin')")
@Transactional
    public List<Object> suppliers() throws Exception {
        try{
            List<Object> supplierList=supplierService.consumeSuppliers();
        
            return supplierList;
        }catch (Exception e){
             throw new Exception();
        }
            }

}

