package org.inventory.api.inventoryApiRest.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.inventory.api.inventoryApiRest.repository.DepartmentRepository;
import org.inventory.api.inventoryApiRest.repository.InventoryRepository;
import org.inventory.api.inventoryApiRest.repository.ProductRepository;
import org.inventory.api.inventoryApiRest.model.Department;
import org.inventory.api.inventoryApiRest.model.Inventory;
import org.inventory.api.inventoryApiRest.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	InventoryRepository inventoryRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public void save(Inventory inventory) {
		Integer department_id=0;
		Department department=null;
		Product product=null;
		try
		{
			inventory.setLastDate(new Date());
			inventory.setLastUser("inicial");
			department_id = inventory.getProductId().getDepartmentId().getId();
			System.out.println("department_id: "+department_id);
			department = departmentRepository.findById(department_id).get();
			System.out.println("department: "+department);
			inventory.getProductId().setDepartmentId(department);
			
			product = productRepository.findByName(inventory.getProductId().getName()) ;
			
			if(product == null)
			{
				product = productRepository.findByBarcode(inventory.getProductId().getBarcode()) ;
			}
			
			if(product != null)
			{
				inventory.getProductId().setId(product.getId());
				System.out.println("El producto ya existente a reemplzar es: "+ product.getId());
			}
						
			productRepository.save(inventory.getProductId());
			inventoryRepository.save(inventory);
		}catch(Exception e)
		{
			System.err.println(e);
			throw e;
		}
	}
	@Override
	public void update(Inventory inventory) {
		// TODO Auto-generated method stub
		inventory.setLastDate(new Date());
		inventory.setLastUser("inicial");
		inventoryRepository.save(inventory);
	}

	@Override
	public List<Inventory> findAll(BigInteger companyId) {
		return (List<Inventory>) inventoryRepository.findByCompanyIdOrderByProductIdDescriptionAsc(companyId);
//		Pageable pageable = PageRequest.of(0, 15,Sort.by("productId.description").ascending());
//		Page<Inventory> page = inventoryRepository.findAll(pageable);
//		return page.getContent();
	}
	
	@Override
	public Inventory findById(long id) {
		return inventoryRepository.findById(id).get();
	}
	
	@Override
	public Inventory findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Inventory findByBarcode(String barcode) {
		// TODO Auto-generated method stub
		Product product = null;
		Inventory inventory;
		product = productRepository.findByBarcode(barcode);
		
		inventory=inventoryRepository.findByProductId(product);						
		return inventory;
	}
	
	public boolean isInventoryExist(Inventory inventory) {				
		Product product = productRepository.findByName(inventory.getProductId().getName()) ;
		return product != null && inventoryRepository.findByProductId(product)!=null ;
	}
	
	public boolean isBarcodeExist(Inventory inventory) {
		Product product = productRepository.findByBarcode(inventory.getProductId().getBarcode()) ;
		return product != null && inventoryRepository.findByProductId(product)!=null;
	}
	
	@Override
	public void deleteById(long id) {//CAMBIAR
		// TODO Auto-generated method stub
		inventoryRepository.deleteById(id);
	}

}
