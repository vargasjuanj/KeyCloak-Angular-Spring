package frm.utn.ecomapp.services;

import java.util.List;

public interface IservicioGenerico<E> {

    E findById(long id) throws Exception;

    E save(E entityForm) throws Exception;

    E update(long id, E entityForm) throws Exception;

    int countPages(int size) throws Exception;

    List<E> findAll(int page, int size) throws Exception;

    List<E> findAll() throws Exception;

    boolean delete(long id) throws Exception;


}