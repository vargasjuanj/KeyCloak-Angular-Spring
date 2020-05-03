package frm.utn;

import frm.utn.entities.Client;
import frm.utn.entities.Product;
import frm.utn.repositories.ClientRepository;
import frm.utn.repositories.ProductRepository;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Application implements CommandLineRunner{
@Autowired
    ProductRepository productRepository;
    @Autowired
   ClientRepository clientRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product( "ordi HP", 850));
        productRepository.save(new Product("imprimante LX HP", 150));
        productRepository.save(new Product("Iphone X ", 1150));
        System.out.println("------ PRODUCTS -----");
        productRepository.findAll().forEach(product -> {
            System.out.println(product.getName());
        });
        
        Stream.of("Marcos", "Ivan", "Gonzalo").forEach(name -> {
            clientRepository.save(new Client( name, "contact@"+name+".ar"));
        });
        System.out.println("----- CLIENTS -----");
        clientRepository.findAll().forEach(client -> {
            System.out.println(client.getName());
        });

    }

}
