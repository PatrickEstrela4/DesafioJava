package br.com.cresol.desafio;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cresol.desafio.resource.EmprestimoResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * @author evandro
 *
 */
@SpringBootApplication
public class DesafioApplication extends Application {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
}
