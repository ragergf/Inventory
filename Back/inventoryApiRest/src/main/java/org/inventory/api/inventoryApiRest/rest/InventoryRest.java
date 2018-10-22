package org.inventory.api.inventoryApiRest.rest;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.inventory.api.inventoryApiRest.model.Inventory;
import org.inventory.api.inventoryApiRest.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class InventoryRest {

	@Autowired
	InventoryService inventoryService;
	 
	//-------------------Retrieve All Inventory--------------------------------------------------------
    
    @RequestMapping(value = "/inventory/", method = RequestMethod.GET)
    public ResponseEntity<List<Inventory>> listAllUsers() {
    	BigInteger companyId = new BigInteger("1");
        List<Inventory> users = inventoryService.findAll(companyId);
        if(users.isEmpty()){
            return new ResponseEntity<List<Inventory>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Inventory>>(users, HttpStatus.OK);
    }
    
    //-------------------Retrieve Single Inventory--------------------------------------------------------
    
    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Inventory> get(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Inventory inventory = inventoryService.findById(id);
        if (inventory == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
    }
    
  //-------------------Create a User--------------------------------------------------------
    
    @RequestMapping(value = "/inventory/", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Inventory inventory,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Inventory " + inventory.getProductId().getName());
 
        if (inventoryService.isInventoryExist(inventory)) {
            System.out.println("A Inventory with name " + inventory.getProductId().getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        
        if (inventoryService.isBarcodeExist(inventory)) {
            System.out.println("A Inventory with barcode " + inventory.getProductId().getBarcode() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.LOCKED);
        }
 
        inventoryService.save(inventory);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/inventory/{id}").buildAndExpand(inventory.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
  //------------------- Update a User --------------------------------------------------------
    
    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Inventory> update(@PathVariable("id") long id, @RequestBody Inventory inventory) {
        System.out.println("Updating Inventory " + id);
         
        Inventory currentInventory = inventoryService.findById(id);
         
        if (currentInventory==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }
        
        if (!currentInventory.getProductId().getName().trim().equalsIgnoreCase(inventory.getProductId().getName().trim()) && inventoryService.isInventoryExist(inventory)) {
            System.out.println("A Inventory with name " + inventory.getProductId().getName() + " already exist");
            return new ResponseEntity<Inventory>(HttpStatus.CONFLICT);
        }
        
        if (!currentInventory.getProductId().getBarcode().trim().equalsIgnoreCase(inventory.getProductId().getBarcode().trim()) && inventoryService.isBarcodeExist(inventory)) {
            System.out.println("A Inventory with barcode " + inventory.getProductId().getBarcode() + " already exist");
            return new ResponseEntity<Inventory>(HttpStatus.LOCKED);
        }
 
        currentInventory.getProductId().setName(inventory.getProductId().getName());
        currentInventory.getProductId().setDescription(inventory.getProductId().getDescription());
        currentInventory.getProductId().setBarcode(inventory.getProductId().getBarcode());
        currentInventory.setQuantity(inventory.getQuantity());
        currentInventory.setPrice(inventory.getPrice());
         
        inventoryService.update(currentInventory);
        return new ResponseEntity<Inventory>(currentInventory, HttpStatus.OK);
    }
    
    //------------------- Delete a User --------------------------------------------------------
    
    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Inventory> delete(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Inventory with id " + id);
 
        Inventory inventory= inventoryService.findById(id);
        if (inventory == null) {
            System.out.println("Unable to delete. Inventory with id " + id + " not found");
            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }
 
        inventoryService.deleteById(id);
        return new ResponseEntity<Inventory>(HttpStatus.NO_CONTENT);
    }
    
    
    @RequestMapping(value = "/inventory/search/findByBarcode", method = RequestMethod.GET)
    public ResponseEntity<Inventory> delete(@RequestParam("barcode") String barcode) {
        System.out.println("Search by barcode: " + barcode);
 
        Inventory inventory= inventoryService.findByBarcode(barcode);
        if (inventory == null) {
            System.out.println("Unable to delete. Inventory with barcode " + barcode + " not found");
            return new ResponseEntity<Inventory>(HttpStatus.NOT_FOUND);
        }
       
        return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
    }

}
