package application;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quantas pessoas serao digitadas? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		double[] alturas = new double[n];
		char[] generos = new char[n];
		int countMen = 0;
		int countWomen = 0;
		double sumHeight = 0.0; 
		double maiorAltura = 0.0;
		double menorAltura = 1000.0;
		
		for (int i = 0; i < alturas.length; i++) {
			System.out.printf("Altura da %da pessoa: ", i+1);
			alturas[i] = sc.nextDouble();
			sc.nextLine();
			
			System.out.printf("Genero da %da pessoa: ", i+1);
			generos[i] = sc.next().charAt(0);
			
			if ((generos[i] == 'F') || (generos[i] == 'f')) {
				countWomen++;
				sumHeight += alturas[i];
			}
			
			if ((generos[i] == 'M') || (generos[i] == 'm')) {
				countMen++;
			}
			
			if (alturas[i] > maiorAltura) {
				maiorAltura = alturas[i];
			}
			if (alturas[i] < menorAltura) {
				menorAltura = alturas[i];
			}
		}
		
		double avgHeight = sumHeight / countWomen;
		System.out.printf("Menor Altura: %.2f\n", menorAltura);
		System.out.printf("Maior Altura: %.2f\n", maiorAltura);
		
		if (countWomen != 0) {
			System.out.printf("Media das alturas das mulheres = %.2f\n", avgHeight);
		} else {
			System.out.println("Nao ha mulheres cadastradas!");
		}
		
		System.out.printf("Numero de homens = %d", countMen);
		
		sc.close();
	}

}
