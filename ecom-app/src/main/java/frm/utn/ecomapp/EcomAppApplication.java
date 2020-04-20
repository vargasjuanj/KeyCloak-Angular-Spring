package frm.utn.ecomapp;

import frm.utn.ecomapp.entities.Product;
import frm.utn.ecomapp.repositories.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication


public class EcomAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomAppApplication.class, args);
	}

/*
-La @Bean anotación Spring dice que un método produce un bean para ser administrado por el contenedor Spring. (instancia en contenedor de spring)
 Es una anotación a nivel de método. Durante la configuración de Java ( @Configuration), el método se ejecuta y su valor de retorno se registra como un bean dentro de a BeanFactory.
-CommandLineRunner es una interfaz funcional utilizada para indicar que un bean debe ejecutarse cuando está contenido dentro de una SpringApplication.
 Una aplicación Spring Boot puede tener múltiples beans implementados CommandLineRunner. Estos se pueden pedir con @Order

 */

	@Bean
	CommandLineRunner start(ProductRepository productRepository ) {
		return  args -> {

			productRepository.save(new Product( "ordi HP", 850));
			productRepository.save(new Product("imprimante LX HP", 150));
			productRepository.save(new Product("Iphone X ", 1150));

			productRepository.findAll().forEach(product -> {
				System.out.println(product.getName());
			});

		};
	}
}
