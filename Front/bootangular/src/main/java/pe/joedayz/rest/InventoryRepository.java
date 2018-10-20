package pe.joedayz.rest;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import pe.joedayz.modelo.Inventory;

@CrossOrigin(origins = "http://localhost:8081")
@RepositoryRestResource(collectionResourceRel = "inventory", path = "inventory")
public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long>  {

}
