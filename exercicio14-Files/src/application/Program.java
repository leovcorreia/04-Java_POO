package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("How many products will be saved? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		List<Product> products = new ArrayList<>();
		
		System.out.println("Enter your products data: ");
		// READING DATA FROM USER
		for (int i = 0; i < n; i++) {
			System.out.print((i+1) + ") Product name: ");
			String name = sc.nextLine();
			System.out.print("Product price: ");
			Double price = sc.nextDouble();
			System.out.print("Product quantity: ");
			Integer quantity = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			
			Product product = new Product(name, price, quantity);
			products.add(product);
		}
		
		System.out.print("\nEnter the folder path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);

		// WRITING IN THE SOURCE FILE
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (Product prod: products) {
				bw.write(prod.getName() + "," + String.format("%.2f", prod.getPrice()) + "," + prod.getQuantity());
				bw.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		/*System.out.println("\nTHE FILE:");
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		String sourcePath = path.getParent();
		new File(sourcePath + "\\out").mkdir(); // creating a subdirectory "out" in the source path

		System.out.print("Enter the output file path: ");
		String outStrPath = sc.nextLine();
		
		File outPath = new File(outStrPath);
		
		// WRITING IN THE OUTPUT FILE
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(outPath))) {
			for (Product prod: products) {
				bw.write(prod.getName() + "," + String.format("%.2f", prod.total()));
				bw.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.close();
	}

}
