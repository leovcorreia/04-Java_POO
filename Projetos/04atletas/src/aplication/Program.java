package aplication;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int qtdAtletas, i;
		double altura, peso;
		String nome, sexo;
		
		System.out.println("Qual a quantidade de atletas?");
		qtdAtletas = sc.nextInt();
		
		for (i = 1; i <= qtdAtletas; i++) {
			System.out.println("Digite os dados do atleta numero " + i + ": \n");
			System.out.println("Nome: ");
			nome = sc.nextLine();
			
			System.out.println("\nSexo: ");
			sexo = sc.nextLine();
			while (!sexo.equals("F") && (!sexo.equals("M"))) {
				System.out.println("Valor invalido! Favor digitar F ou M: ");
				sexo = sc.nextLine();
			}
			
			System.out.println("\nAltura: ");
			altura = sc.nextDouble();
			while (altura < 0) {
				System.out.println("Valor invalido! Favor digitar um valor positivo: ");
				altura = sc.nextDouble();
			}
			
			System.out.println("\nPeso: ");
			peso = sc.nextDouble();
			while (peso < 0) {
				System.out.println("Valor invalido! Favor digitar um valor positivo: ");
				peso = sc.nextDouble();
			}
		}
		
		System.out.println("\nRELATORIO: ");
		
		
		sc.close();
	}

}
