package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Account;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter account number: ");
		int number = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		System.out.println("Enter account holder: ");
		String holderName = sc.nextLine();
		System.out.println("Is there an initial deposit (y/n)? ");
		char verification = sc.next().charAt(0);
		
		double initialDeposit = 0.0;
		if ((verification == 'y') || (verification == 'Y')) {
			System.out.println("Enter initial deposit value: ");
			initialDeposit = sc.nextDouble();
		}
		
		Account ac = new Account(number, holderName, initialDeposit); // Construtor
		
		System.out.println(ac);
		
		sc.close();
	}

}
