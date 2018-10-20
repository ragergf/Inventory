package pe.joedayz.rest;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import pe.joedayz.modelo.Department;

@CrossOrigin(origins = "http://localhost:8081")
@RepositoryRestResource(collectionResourceRel = "department", path = "department")
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
	
}
