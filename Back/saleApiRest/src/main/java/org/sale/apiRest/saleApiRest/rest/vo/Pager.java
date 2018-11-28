package org.sale.apiRest.saleApiRest.rest.vo;

import java.util.List;

import org.sale.apiRest.saleApiRest.model.Sale;

public class Pager<T> {
	private int page;
	private int size;
	private List<T> arrayFilter;
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
	public List<T> getArrayFilter() {
		return arrayFilter;
	}
	public void setArrayFilter(List<T> arrayFilter) {
		this.arrayFilter = arrayFilter;
	}
	public long getArrayLength() {
		return arrayLength;
	}
	public void setArrayLength(long arrayLength) {
		this.arrayLength = arrayLength;
	}
	
	
}
