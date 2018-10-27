package org.inventory.api.inventoryApiRest;

import javax.annotation.PostConstruct;

import org.inventory.api.inventoryApiRest.model.Department;
import org.inventory.api.inventoryApiRest.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class InventoryApiRestApplication {
	
	@Autowired
	DepartmentRepository repository;
	
	@PostConstruct
	public void init()
	{
		Department department = new Department();
		department.setName("farmaceutico");
		repository.save(department);		
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryApiRestApplication.class, args);
	}
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("http://localhost:8081");
	            }
	        };
	    }
}
