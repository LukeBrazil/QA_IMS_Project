package com.qa.ims;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class SqlCon {
	private String url;
	private String username;
	private String password;
	
	public SqlCon() {
		this.url = "jdbc:mysql://localhost:3306/qa_ims";
		this.username = "root";
		this.password = "mysql";
	}

	void getCon() {
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
	
	void addCustomer(String first_name, String last_name, int age) {
		//INSERT INTO Customers(first_name, last_name, age) VALUES ('John', 'Mable', 19);
		Statement stmt = null;
		ResultSet rslt = null;
		
		

		try {
			
			Connection con = null;
	
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
			String query = "INSERT INTO Customers(first_name, last_name, age) VALUES(\'" + first_name + "\', '" + last_name + "\', " + age + ")";
	
			stmt.execute(query);
//			while (rslt.next()) {
//				System.out.println("Executed!");
//
//			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
