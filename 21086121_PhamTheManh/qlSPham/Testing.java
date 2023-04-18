package qlSPham;

import java.util.Scanner;

public class Testing {

	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		int choice;
		ManageProduct dom = new ManageProduct();
		boolean flag = true;
		do {
			createMenu();
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				sc.nextLine();
				Product p = createNewProduct();
				dom.addProduct(p);
				break;
			case 2:
				sc.nextLine();
				System.out.println("Nhap ID: ");
				String id = sc.nextLine();
				dom.deleteProduct(id);
				break;
			case 3:
				sc.nextLine();
				System.out.println("Nhap ID: ");
				id = sc.nextLine();
				System.out.println("Nhap gia tien: ");
				double price = sc.nextDouble();
				dom.updatePrice(id, price);
				break;
			case 4:
				dom.printAll();
			case 5:
				dom.writeXMLFile();
				break;
			default:
				flag = false;
				break;
			}
		}
		while (flag);
	}

	private static Product createNewProduct() { 
		System.out.println("Enter productID: "); 
		String productID = sc.nextLine(); System.out.println("Enter name: "); 
		String name = sc.nextLine();
		System.out.println("Enter manufacture: "); 
		String manufacture = sc.nextLine(); 
		System.out.println("Enter description: "); 
		String description= sc.nextLine(); 
		Supplier supplier = createNewSupplier(); System.out.println("Enter price: "); double price = sc. nextDouble();
		return new Product (productID, name, manufacture, description, supplier, price);
	}
	
	private static Supplier createNewSupplier() {
		System.out.println("Enter supplier name: ");
		String name = sc.nextLine();
		System.out.println("Enter country: "); 
		String country= sc.nextLine();
		System.out.println("Enter website: ");
		String website = sc.nextLine();
		return new Supplier (name, country, website);
	}
	
	
	private static void createMenu() {
		System.out.println("---Menu---");
		System.out.println("1. Add product");
		System.out.println("2. Delete product");
		System.out.println("3. Update price");
		System.out.println("4. Print all");
		System.out.println("5. Write XML file");
		System.out.println("0. Exit");
		System.out.println("Your choice: ");
		
	}
}


