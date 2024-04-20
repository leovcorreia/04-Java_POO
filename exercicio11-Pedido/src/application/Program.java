package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		// List<OrderItem> itens = new ArrayList<>();
		
		System.out.println("Enter client data: ");
		System.out.print("Name: ");
		String clientName = sc.nextLine();
		
		System.out.print("Email: ");
		String clientEmail = sc.nextLine();
		
		System.out.print("Birth date (DD/MM/YYYY): ");
		Date clientBirth = sdf1.parse(sc.next());
		
		Client client = new Client(clientName, clientEmail, clientBirth);
		
		System.out.println("Enter order data: ");
		System.out.print("Status: ");
		OrderStatus os1 = OrderStatus.valueOf(sc.next());
		System.out.print("How many items to this order? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		Date orderMoment = new Date();
		Order order = new Order(orderMoment, os1, client);
		
		for (int i = 0; i < n; i++) {
			System.out.println("Enter #" + (i+1) + " item data: ");
			
			System.out.print("Product name: ");
			String productName = sc.nextLine();
			
			System.out.print("Product price: ");
			Double productPrice = sc.nextDouble();
			
			System.out.print("Quantity: ");
			Integer quantity = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			
			Product product = new Product(productName, productPrice);
			OrderItem item = new OrderItem(quantity, productPrice, product);
			order.addItem(item);
		}
		
		System.out.println("\nORDER SUMMARY: ");
		System.out.println(order);
		System.out.println("Client: " + client);
		System.out.println("Order itens: ");
		
		System.out.println("Total price: $" + String.format("%.2f", order.total()));
		
		sc.close();
	}

}
