package com.qa.ims;

public abstract class Item {
	
	String name;
	double value;
	int inStock;
	
	public Item(String name, double value, int inStock) {
		this.name = name;
		this.value = value;
		this.inStock = inStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", value=" + value + ", inStock=" + inStock + "]";
	}
	
	
}
