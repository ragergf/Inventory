package org.inventory.api.inventoryApiRest.repository;

import java.util.List;

import org.inventory.api.inventoryApiRest.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:8081")
//@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>{
	Product findByBarcode(String barcode);
	Product findByName(String name);
	List<Product> findAllByOrderByDescription();
}
