package frm.utn.suplierservice;

import frm.utn.suplierservice.entities.Supplier;
import frm.utn.suplierservice.repositories.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
public class SuplierServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuplierServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration) {
		return args -> {
			restConfiguration.exposeIdsFor(Supplier.class);


			Stream.of("IBM", "HP", "Apple").forEach(name -> {
				supplierRepository.save(new Supplier( name, "contact@"+name+".fr"));
			});

			supplierRepository.findAll().forEach(supplier -> {
				System.out.println(supplier.getName());
			});

		};

	}
}
