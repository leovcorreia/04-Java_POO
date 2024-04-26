package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("How many items will be saved? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		String[] items = new String[n];
		Double[] price = new Double[n];
		
		// READING DATA FROM USER
		for (int i = 0; i < n; i++) {
			System.out.print("Enter your item data: ");
			String item = sc.nextLine();
			items = item.split(",");
			price[i] = items;
		}
		
		System.out.println("Split: ");
		for (String palavra : items) {
		    System.out.println(palavra);
		}
		
		System.out.print("\nEnter the folder path: ");
		String strPath = sc.nextLine();

		File path = new File(strPath);

		// WRITING IN THE SOURCE FILE
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (int i = 1; i <= 3*n; i++) { // REPRESENTS THE ITEMS ARRAY
				bw.write(items[i-1]);
				if (i % 3 == 0) {
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("\nTHE FILE:");
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		String sourcePath = path.getParent();
		new File(sourcePath + "\\out").mkdir(); // creating a subdirectory "out" in the source path

		System.out.print("Enter the output file path: ");
		String outPath = sc.nextLine();
		
		sc.close();
	}

}
