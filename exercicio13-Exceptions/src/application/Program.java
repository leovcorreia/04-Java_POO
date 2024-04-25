package application;

import java.util.Locale;
import java.util.Scanner;

import model.entities.Account;
import model.exception.DomainException;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("Enter account data: ");
			System.out.print("Number: ");
			Integer number = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			System.out.print("Holder: ");
			String holder = sc.nextLine();
			System.out.print("Initial balance: ");
			Double balance = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			System.out.print("Withdraw limit: ");
			Double withdrawLimit = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			Account ac = new Account(number, holder, balance, withdrawLimit);
			
			System.out.print("\nEnter amount for withdraw: ");
			Double withdraw = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			ac.withdraw(withdraw);
			System.out.println(ac);
		}
		catch (DomainException e) {
			System.out.println("Withdraw error: " + e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("Unexpected error!");
		}
		
		sc.close();
	}

}
