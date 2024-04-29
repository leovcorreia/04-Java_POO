package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> list = new ArrayList<>();
		
		System.out.print("How many products will be saved? ");
		int n = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Enter the folder path: ");
		String sourceStrFile = sc.nextLine();
		
		File sourcePathFile = new File(sourceStrFile);
		
		String sourceStrFolder = sourcePathFile.getParent();
		boolean success = new File(sourceStrFolder + "\\out").mkdir();
		
		String targetStrFile = sourceStrFolder + "\\out\\summary.csv";
	
		// WRITING IN THE SOURCE FILE
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(sourceStrFile))){
			for (int i = 0; i < n; i++) {
				System.out.print("Enter your " + (i+1) + ") product data: ");
				String product = sc.nextLine();
				bw.write(product);
				bw.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// READING THE SOURCE FILE AND WRITING IN THE TARGET FILE
		try (BufferedReader br = new BufferedReader(new FileReader(sourceStrFile))) {
			
			String item = br.readLine();
			while (item != null) {
				String[] fields = item.split(",");
				String name = fields[0];
				Double price = Double.parseDouble(fields[1]);
				Integer quantity = Integer.parseInt(fields[2]);
				
				Product prod = new Product(name, price, quantity);
				list.add(prod);
				
				item = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetStrFile))) {
				// WRITING IN THE TARGET FILE 
				for (Product prod: list) {
					bw.write(prod.getName() + "," + String.format("%.2f", prod.total()));
					bw.newLine();
				}
				
				System.out.println(targetStrFile + " CREATED!");
				
			}
			catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}
		}
		catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
		
		sc.close();
	}
}