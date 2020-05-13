package frm.utn.services;

import frm.utn.entities.Product;
import frm.utn.repositories.ProductRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServicioGenerico<Product, ProductRepository> {
   @Override
    public Product update(long id, Product product) throws Exception {

        try {
            Optional<Product> entityOptional = this.repository.findById(id);
            product.setId(entityOptional.get().getId());
            product = this.repository.save(product);

            return product;

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }
}