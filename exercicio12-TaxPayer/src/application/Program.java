package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Company;
import entities.Individual;
import entities.TaxPayer;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<TaxPayer> list = new ArrayList<>();
		
		System.out.print("Enter the number of tax payers: ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		for (int i = 0; i < n; i++) {
			System.out.println("Tax payer #" + (i+1) + " data: ");
			System.out.print("Individual or company (i/c)? ");
			char type = sc.next().charAt(0);
			while ((type != 'i') && (type != 'c')) {
				sc.nextLine(); // Limpeza buffer
				System.out.print("Not valid! Try again: ");
				type = sc.next().charAt(0);
			}
			sc.nextLine(); // Limpeza buffer
			
			System.out.print("Name: ");
			String name = sc.nextLine();
			System.out.print("Anual income: ");
			Double anualIncome = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			if (type == 'i') {
				System.out.print("Health expenditures: ");
				Double healthExpenditures = sc.nextDouble();
				sc.nextLine(); // Limpeza buffer
				list.add(new Individual(name, anualIncome, healthExpenditures));
			} else {
				System.out.print("Number of employees: ");
				int numberEmployees = sc.nextInt();
				sc.nextLine(); // Limpeza buffer
				list.add(new Company(name, anualIncome, numberEmployees));
			}
		}
		
		System.out.println("\nTAXES PAID: ");
		Double sum = 0.0;
		for (TaxPayer payer: list) {
			System.out.println(payer.getName() + ": $ " + String.format("%.2f", payer.tax()));
			sum += payer.tax();
		}
		
		System.out.println("\nTOTAL TAXES: $ " + String.format("%.2f", sum));
		
		sc.close();
	}

}
