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

	void showAllCustomers() {
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

	void addCustomer() {
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

}
