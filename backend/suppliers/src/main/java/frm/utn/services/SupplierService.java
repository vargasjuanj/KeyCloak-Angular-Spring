package frm.utn.services;

import java.util.Optional;

import frm.utn.entities.Supplier;
import frm.utn.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends ServicioGenerico<Supplier, SupplierRepository> {
@Override
    public Supplier update(long id, Supplier supplier) throws Exception {

        try {
            Optional<Supplier> entityOptional = this.repository.findById(id);
            supplier.setId(entityOptional.get().getId());
            supplier = this.repository.save(supplier);

            return supplier;

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }
}
