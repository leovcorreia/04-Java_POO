package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Serao digitados dados de quantos produtos? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		Product[] prod = new Product[n];
		
		for (int i = 0; i < prod.length; i++) {
			System.out.printf("Produto %d:\n", i+1);
			
			System.out.printf("Nome: ");
			String name = sc.nextLine();
			
			System.out.printf("Preco de compra: ");
			double purchase = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			System.out.printf("Preco de venda: ");
			double sale = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			double profit = sale - purchase;
			double profitPercentage = profit / purchase * 100.0;
			
			prod[i] = new Product(name, purchase, sale, profit, profitPercentage);
		}
		
		System.out.println("\nRELATORIO:");
		int countLess10 = 0;
		int countBetween10_20 = 0;
		int countMore20 = 0;
		double sumPurchase = 0.0;
		double sumSale = 0.0;
		double totalProfit = 0.0;
		
		for (int i = 0; i < prod.length; i++) {
			if (prod[i].getProfitPercentage() < 10.0) {
				countLess10++;
			} else if (prod[i].getProfitPercentage() <= 20.0) {
				countBetween10_20++;
			} else {
				countMore20++;
			}
			
			sumPurchase += prod[i].getPurchase();
			sumSale += prod[i].getSale();
			totalProfit = sumSale - sumPurchase;
		}
		
		System.out.printf("Lucro abaixo de 10%%: %d\n", countLess10);
		System.out.printf("Lucro entre 10%% e 20%%: %d\n", countBetween10_20);
		System.out.printf("Lucro acima de 20%%: %d\n", countMore20);
		System.out.printf("Valor total de compra: %.2f\n", sumPurchase);
		System.out.printf("Valor total de venda: %.2f\n", sumSale);
		System.out.printf("Lucro total: %.2f", totalProfit);
		
		sc.close();
	}

}
