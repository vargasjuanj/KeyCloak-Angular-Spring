package frm.utn.ecomapp.repositories;

import frm.utn.ecomapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product,Long>{
}
