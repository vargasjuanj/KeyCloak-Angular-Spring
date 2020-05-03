package frm.utn;


import frm.utn.entities.Supplier;

import frm.utn.repositories.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner start(SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Supplier.class);


            Stream.of("IBM", "HP", "Apple").forEach(name -> {
                supplierRepository.save(new Supplier( name, "contact@"+name+".fr"));
            });
            System.out.println("----- SUPPLIERS -----");
            supplierRepository.findAll().forEach(supplier -> {
                System.out.println(supplier.getName());
            });
          


        };

    }



}
