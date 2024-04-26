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
		
		String[] items = new String[n];
		
		for (int i = 0; i < n; i++) {
			System.out.print("Enter your item data: ");
			items[i] = sc.nextLine();
		}
		
		System.out.print("Enter the folder path: ");
		String strPath = sc.nextLine();
		
		File path = new File(strPath);
		
		// WRITING IN THE SOURCE FILE
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			for (String item: items) {
				bw.write(item);
				bw.newLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean sucess = new File(strPath + "\\out").mkdir();
		if (sucess) {
			
		}
		
		
		sc.close();
	}

}
