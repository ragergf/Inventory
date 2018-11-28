package org.inventory.api.inventoryApiRest.rest.vo;

import java.util.List;

import org.inventory.api.inventoryApiRest.model.Inventory;

public class Pager {
	private int page;
	private int size;
	private List<Inventory> arrayFilter;
	private long arrayLength;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<Inventory> getArrayFilter() {
		return arrayFilter;
	}
	public void setArrayFilter(List<Inventory> arrayFilter) {
		this.arrayFilter = arrayFilter;
	}
	public long getArrayLength() {
		return arrayLength;
	}
	public void setArrayLength(long arrayLength) {
		this.arrayLength = arrayLength;
	}
	
	
}
