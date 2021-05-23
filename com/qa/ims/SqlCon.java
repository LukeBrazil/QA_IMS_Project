package com.qa.ims;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SqlCon {
	private String url;
	private String username;
	private String password;

	public SqlCon() {
		this.url = "jdbc:mysql://localhost:3306/qa_ims";
		this.username = "root";
		this.password = "mysql";
	}

	public void showAllCustomers() {
		Statement stmt = null;
		ResultSet rslt = null;

		try {

			Connection con = null;

			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rslt = stmt.executeQuery("SELECT * FROM customers");
			while (rslt.next()) {
				int id = rslt.getInt("customer_id");
				String firstName = rslt.getString("first_name");
				String lastName = rslt.getString("last_name");
				int age = rslt.getInt("age");
				String response = "ID: " + id + " First Name: " + firstName + " Last Name: " + lastName + " age: "
						+ age;
				System.out.println(response);

			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void addCustomer() {
		// INSERT INTO Customers(first_name, last_name, age) VALUES ('John', 'Mable',
		// 19);
		Statement stmt = null;
		Connection con = null;

		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter First Name: ");
		String first_name = myObj.next();
		System.out.println(first_name);
		System.out.println("Enter Last Name: ");
		String last_name = myObj.next();
		System.out.println(last_name);
		System.out.println("Enter Age: ");
		int age = myObj.nextInt();
		System.out.println(age);
		
		Customer newCustomer = new Customer(first_name, last_name, age);

		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			String query = "INSERT INTO Customers(first_name, last_name, age) VALUES(\'" + newCustomer.getFirst_name() + "\', '"
					+ newCustomer.getLast_name() + "\', " + newCustomer.getAge() + ")";
			stmt.execute(query);
			System.out.println("Added Cusomter!");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void showItems() {
		Statement stmt = null;
		ResultSet rslt = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rslt = stmt.executeQuery("SELECT * FROM item");
			while(rslt.next()) {
				int item_id = rslt.getInt("item_id");
				String item_name = rslt.getString("item_name");
				int item_value = rslt.getInt("item_value");
				String response = "ID: " + item_id + " item_name: " + item_name + " item_value: " + item_value;
				System.out.println(response);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void createOrder() {
		Statement stmt = null;
		Connection con = null;
		System.out.println("Create New Order... Choose Customer by Id: \n");
		SqlCon myCon = new SqlCon();
		@SuppressWarnings("resource")
		Scanner myObj = new Scanner(System.in);
		myCon.showAllCustomers();
		int customerChosen = myObj.nextInt();
		
		Order newOrder = new Order(customerChosen);
		
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			String query = "INSERT INTO Orders(fk_customer_id) VALUES(" + newOrder.getFk_customer_id() + ")";
			stmt.execute(query);
			System.out.println("Added order!");
		} catch (SQLException ex) { 
			ex.printStackTrace();
		}
		
	}
	
	public void showOrders() {
		Statement stmt = null;
		ResultSet rslt = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			rslt = stmt.executeQuery("SELECT s.first_name, s.last_name, o.order_id, o.fk_customer_id, o.isComplete\r\n"
					+ "	from customers s\r\n"
					+ "		INNER JOIN orders o ON s.customer_id = o.fk_customer_id;");
			while(rslt.next()) {
				int order_id = rslt.getInt("order_id");
				String first_name = rslt.getString("first_name");
				String last_name = rslt.getString("last_name");
				int customer_id = rslt.getInt("fk_customer_id");
				
				String response = "Order Id: " + order_id + " first_name: " + first_name + " last_name: " + last_name + " customer_id " + customer_id;
				System.out.println(response);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void addItemsToOrder() {
		boolean orderComplete = false;
		SqlCon myCon = new SqlCon();
		Scanner myObj = new Scanner(System.in);
		char option = '\0';
		
		do {
			Statement stmt = null;
			Connection con = null;
			System.out.println("Please choose an Order to add items.");
			myCon.showOrders();
			int orderChosen = myObj.nextInt();
			System.out.println("Please Choose an item by id to add to the order.");
			myCon.showItems();
			int itemChosen = myObj.nextInt();
			
			try {
				con = DriverManager.getConnection(url, username, password);
				stmt = con.createStatement();
				String query = "INSERT INTO order_items(fk_item_id, fk_order_id) VALUES (" + itemChosen + ", " + orderChosen + ")";
				stmt.execute(query);
				System.out.println("Item added to order!");
				System.out.println("Would you like to add more items?");
				char chosenDecision = myObj.next().charAt(0);
				option = Character.toUpperCase(chosenDecision);
				if (option == 'N') {
					orderComplete = true;
					break;
				}
				
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} while(orderComplete == false);
	}

}
