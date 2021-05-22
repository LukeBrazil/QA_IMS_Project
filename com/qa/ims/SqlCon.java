package com.qa.ims;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class SqlCon {

	void getCon() {
		Statement stmt = null;
		ResultSet rslt = null;

		try {
			String url = "jdbc:mysql://localhost:3306/qa_ims";
			Connection con = null;
			String username = "root";
			String password = "mysql";

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
	
	void addCustomer(Object c) {
		//INSERT INTO Customers(first_name, last_name, age) VALUES ('John', 'Mable', 19);
		Statement stmt = null;
		ResultSet rslt = null;

		try {
			String url = "jdbc:mysql://localhost:3306/qa_ims";
			Connection con = null;
			String username = "root";
			String password = "mysql";

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
}
