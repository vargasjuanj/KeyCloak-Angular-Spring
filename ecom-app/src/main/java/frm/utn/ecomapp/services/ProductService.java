package frm.utn.ecomapp.services;

import frm.utn.ecomapp.entities.Product;
import frm.utn.ecomapp.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServicioGenerico<Product, ProductRepository> {
}
