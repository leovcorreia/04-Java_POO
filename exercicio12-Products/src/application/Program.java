package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter the number of products: ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		List<Product> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			System.out.println("Product #" + (i+1) + " data: ");
			System.out.print("Common, used or imported (c/u/i)? ");
			char type = sc.next().charAt(0);
			while ((type != 'c') && (type != 'u') && (type != 'i')) {
				System.out.print("Not valid! Try again: ");
				type = sc.next().charAt(0);
			}
			sc.nextLine(); // Limpeza buffer
			
			System.out.print("Name: ");
			String productName = sc.nextLine();
			System.out.print("Price: ");
			Double productPrice = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			if (type == 'i') {
				System.out.print("Customs fee: ");
				Double customsFee = sc.nextDouble();
				sc.nextLine(); // Limpeza buffer
				Product prod = new ImportedProduct(productName, productPrice, customsFee);
				list.add(prod);
			} else if (type == 'u') {
				System.out.print("Manufacture date (DD/MM/YYYY): ");
				Date productDate = sdf.parse(sc.next());
				Product prod = new UsedProduct(productName, productPrice, productDate);
				list.add(prod);
			} else {
				Product prod = new Product(productName, productPrice);
				list.add(prod);
			}
		}
		
		System.out.println("\nPRICE TAGS: ");
		for (Product prod: list) {
			System.out.println(prod.priceTag());
		}
		
		sc.close();
	}

}
