package frm.utn.services;


import frm.utn.entities.Product;
import frm.utn.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServicioGenerico<Product, ProductRepository> {
}
