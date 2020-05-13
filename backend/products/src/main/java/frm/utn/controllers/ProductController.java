package frm.utn.controllers;

import frm.utn.entities.Product;
import frm.utn.services.ProductService;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/ecom/products")
@PreAuthorize("isAuthenticated()") //Abarca la clase en general, despues cada metodo puede especificar que rol tiene permiso
public class ProductController {
   @Autowired
  private ProductService productService;

  //@PreAuthorize("hasAnyRole('ROLE_admin'.'ROLE_user')")
  @GetMapping("/") //A este endpoint pueden acceder todos los autenticados, sin importar si tiene o no un rol
  @Transactional
  public ResponseEntity<?> getAll() throws Exception {
   try{
     return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
 
   }catch(Exception e){
     return ResponseEntity.status(HttpStatus.NOT_FOUND)
     .body("{\"Mi mensaje get todos\": \"" + e.getMessage() + "\"}");
   }
   }

@PostMapping("/")
@PreAuthorize("hasAnyRole('ROLE_admin')")

    @Transactional
    public ResponseEntity<?> post(@RequestBody Product product) {

        try {

            return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"Mi mensaje post\": \"" + e.getMessage() + "\"}");

        }

    }

    @PutMapping("/{id}")
   @PreAuthorize("hasAnyRole('ROLE_admin')")
   @Transactional
   public ResponseEntity<?> put(@PathVariable long id, @RequestBody Product product) {

       try {

           return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, product));

       } catch (Exception e) {

           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("{\"Mi mensaje put\": \"" + e.getMessage() + "\"}");
       }

   }
   @DeleteMapping("/{id}")
   @PreAuthorize("hasAnyRole('ROLE_admin')")

   @Transactional
   public ResponseEntity<?> delete(@PathVariable long id) {

       try {

           return ResponseEntity.status(HttpStatus.OK).body(productService.delete(id));

       } catch (Exception e) {

           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("{\"Mi mensaje put\": \"" + e.getMessage() + "\"}");
       }

   }
   @PreAuthorize("hasAnyRole('ROLE_admin')")
   @GetMapping("/{id}")
   @Transactional
   public ResponseEntity<?> getOne(@PathVariable long id) {

       try {

           return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));

       } catch (Exception e) {

           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("{\"Mi mensaje get uno\": \"" + e.getMessage() + "\"}");

       }

   }

}