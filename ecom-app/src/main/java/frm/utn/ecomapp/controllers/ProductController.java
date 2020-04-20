package frm.utn.ecomapp.controllers;
import frm.utn.ecomapp.entities.Product;

import frm.utn.ecomapp.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller  //Puedo retornar plantillas html, para usarlas con thymeleaft
@RequestMapping("api/v1/ecom") //Si hay una template index me  redirige a ella, me permite tambien no mostrar los objetos del inicio que se crean en el main. Establece una base url para una clase. Se puede usar en métodos también.
public class ProductController extends ControllerGenerico<Product, ProductService> {
    //Sobre thymeleaf https://www.arquitecturajava.com/spring-boot-thymeleaf-y-su-configuracion/


    @GetMapping("/index")
    public String index() {
        return "index";  //Retorna alguna template html
    }

    @GetMapping("/products") //Este endpoint esta asegurado en la clase  KeycloakSpringSecuriteConfig, pero se pueden restringir aca con una anotación
    public String products(Model model) throws Exception {
         model.addAttribute("products", service.findAll());
        return "products";
    }







}