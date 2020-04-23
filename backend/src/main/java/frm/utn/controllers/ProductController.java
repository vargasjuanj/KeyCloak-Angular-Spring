package frm.utn.controllers;



import frm.utn.entities.Product;
import frm.utn.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/ecom")
public class ProductController {
@Autowired
ProductService productService;

    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/products")
    public List<Product>  getProducts( ) throws Exception {
        System.out.println("USUARIO Y ADMIN");
        return productService.findAll();
    }



}
