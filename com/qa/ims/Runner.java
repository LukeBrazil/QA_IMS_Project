package com.qa.ims;
import java.util.Scanner;

public class Runner {
	
	static void menuMessage() {
		System.out.println("Welcome to the Inventory Managment System!");
		System.out.println();
		System.out.println("Please Choose an option below to continue: ");
		System.out.println();
		System.out.println("A: Add New Customer.");
		System.out.println();
		System.out.println("B: Show All Customers.");
		System.out.println();
		System.out.println("C: Show All Items.");
		System.out.println();
		System.out.println("D: Create New Order.");
		System.out.println();
		System.out.println("E: Add Item to Order.");
		System.out.println();
		System.out.println("F: Show All Orders.");
		System.out.println();
		System.out.println("G: Exit.");
		
	}
	
	static void initiatePortal() {
		char option = '\0';
		menuMessage();
		
		do {
			SqlCon myCon = new SqlCon();
			Scanner myInput = new Scanner(System.in);
			char chosenInput = myInput.next().charAt(0);
			option = Character.toUpperCase(chosenInput);
			switch(option) {
			case 'A':
				myCon.addCustomer();
				menuMessage();
				break;
			case 'B':
				myCon.showAllCustomers();
				menuMessage();
				break;
			case 'C':
				myCon.showItems();
				menuMessage();
				break;
			case 'D':
				myCon.createOrder();
				menuMessage();
				break;
			case 'E':
				myCon.addItemsToOrder();
				menuMessage();
				break;
			case 'F':
				myCon.showOrders();
				menuMessage();
				break;
			default:
				System.out.println("Please Choose a valid option: A, B, C, D, E, or F.");
				menuMessage();
				break;
			}
		} while(option != 'G');
		System.out.println("Thank you for using the inventory managment system!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initiatePortal();
	}

}
