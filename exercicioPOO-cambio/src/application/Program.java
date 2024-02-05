package application;

import java.util.Locale;
import java.util.Scanner;
import entities.CurrencyConverter;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Qual o valor do dólar? ");
		double dollarPrice = sc.nextDouble();
		System.out.println("Quantos dólares serão comprados? ");
		double amount = sc.nextDouble();
		
		System.out.printf("Valor a ser pago em reais = %.2f", CurrencyConverter.convertedValue(dollarPrice, amount));
	
		sc.close();
	}

}
