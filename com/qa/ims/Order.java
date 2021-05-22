package com.qa.ims;

public class Order {
	int fk_customer_id;
	
	public Order(int fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}

	public int getFk_customer_id() {
		return fk_customer_id;
	}

	public void setFk_customer_id(int fk_customer_id) {
		this.fk_customer_id = fk_customer_id;
	}

	@Override
	public String toString() {
		return "Order [fk_customer_id=" + fk_customer_id + ", getFk_customer_id()=" + getFk_customer_id() + "]";
	}
	
	
}
