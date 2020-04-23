package frm.utn;

import frm.utn.entities.Product;
import frm.utn.entities.Supplier;
import frm.utn.repositories.ProductRepository;
import frm.utn.repositories.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Bean
    CommandLineRunner start(SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration, ProductRepository productRepository) {
        return args -> {
            restConfiguration.exposeIdsFor(Supplier.class);


            Stream.of("IBM", "HP", "Apple").forEach(name -> {
                supplierRepository.save(new Supplier( name, "contact@"+name+".fr"));
            });
            System.out.println("Suppliers");
            supplierRepository.findAll().forEach(supplier -> {
                System.out.println(supplier.getName());
            });
            productRepository.save(new Product( "ordi HP", 850));
            productRepository.save(new Product("imprimante LX HP", 150));
            productRepository.save(new Product("Iphone X ", 1150));
            System.out.println("Products");
            productRepository.findAll().forEach(product -> {
                System.out.println(product.getName());
            });


        };

    }
    @Bean
    CommandLineRunner start( ) {
        return  args -> {




        };
    }
}
