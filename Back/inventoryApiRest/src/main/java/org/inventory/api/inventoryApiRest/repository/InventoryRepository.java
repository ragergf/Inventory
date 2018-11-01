package org.inventory.api.inventoryApiRest.repository;

import java.math.BigInteger;
import java.util.List;

import org.inventory.api.inventoryApiRest.model.Inventory;
import org.inventory.api.inventoryApiRest.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:8081")
//@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long>  {
	List<Inventory> findByCompanyIdOrderByProductIdDescriptionAsc(BigInteger companyId);
	Inventory findByProductId(Product product);	
	Page<Inventory> findAll(Pageable pageable);
}
