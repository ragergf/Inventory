package org.inventory.api.inventoryApiRest.service;

import java.math.BigInteger;
import java.util.List;

import org.inventory.api.inventoryApiRest.model.Inventory;
import org.inventory.api.inventoryApiRest.rest.vo.Pager;

public interface InventoryService {
	void save(Inventory inventory);
	void update(Inventory inventory);
	void deleteById(long id);
	List<Inventory> findAll(BigInteger companyId);
	List<Inventory> findAll(BigInteger companyId, Pager pager);
	Inventory findById(long id);
	Inventory findByName(String name);
	Inventory findByBarcode(String barcode);
	public boolean isInventoryExist(Inventory invetory);
	public boolean isBarcodeExist(Inventory invetory);
	long count();
}
