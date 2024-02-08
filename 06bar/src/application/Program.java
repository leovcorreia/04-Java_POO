package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Bill;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Bill bill = new Bill();
		
		System.out.printf("Sexo: ");
		bill.gender = sc.next().charAt(0);
		System.out.printf("Quantidade de cervejas: ");
		bill.beer = sc.nextInt();
		System.out.printf("Quantidade de refrigerante: ");
		bill.softDrink = sc.nextInt();
		System.out.printf("Quantidade de espetinho: ");
		bill.barbecue = sc.nextInt();
		
		System.out.println("\nRELATORIO DA COMPRA: \n");
		System.out.printf("Consumo = R$ " + String.format("%.2f", bill.feeding()));
		
		if (bill.cover() == 0.0) {
			System.out.printf("\nIsento de Couvert");
		} else {
			System.out.printf("\nCouvert = R$ " + String.format("%.2f", bill.cover()));
		}
		
		System.out.printf("\nIngresso = R$ " + String.format("%.2f\n", bill.ticket()));
		System.out.printf("\nValor a pagar = R$ " + String.format("%.2f", bill.total()));
		
		sc.close();
	}

}
