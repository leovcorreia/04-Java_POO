package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.TaxPayer;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Quantos contribuintes você vai digitar? ");
		int n = sc.nextInt();
		sc.nextLine(); 
		
		List<TaxPayer> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			System.out.println("\nDigite os dados do " + (i+1) + "o contribuinte: ");
			
			System.out.print("Renda anual com salário: ");
			Double rendaAnualSalario = sc.nextDouble();
			sc.nextLine();
			
			System.out.print("Renda anual com prestação de serviço: ");
			Double rendaAnualServico = sc.nextDouble();
			sc.nextLine();
			
			System.out.print("Renda anual com ganho de capital: ");
			Double rendaAnualCapital = sc.nextDouble();
			sc.nextLine();
			
			System.out.print("Gastos médicos: ");
			Double gastosMedicos = sc.nextDouble();
			sc.nextLine();
			
			System.out.print("Gastos educacionais: ");
			Double gastosEduca = sc.nextDouble();
			sc.nextLine();
			
			TaxPayer payer = new TaxPayer(rendaAnualSalario, rendaAnualServico,
					rendaAnualCapital, gastosMedicos, gastosEduca);
			
			list.add(payer);
			
		}
		
		int i = 1;
		for (TaxPayer payer: list) {
			System.out.println("\nResumo do " + (i) + "o contribuinte: ");
			System.out.printf("Imposto bruto total: " + String.format("%.2f", payer.grossTax()));
			System.out.printf("\nAbatimento: " + String.format("%.2f", payer.taxRebate()));
			System.out.printf("\nImposto devido: " + String.format("%.2f", payer.netTax()) + "\n");
			i++;
		}
		
		sc.close();
	}

}
