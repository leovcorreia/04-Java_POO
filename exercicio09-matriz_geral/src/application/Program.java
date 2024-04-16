package application;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Qual a ordem da matriz? ");
		int n = sc.nextInt();
		sc.nextLine();
		
		double[][] mat = new double[n][n];
		double positiveSum = 0.0;
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.printf("Elemento [" + i + "," + j + "]: ");
				mat[i][j] = sc.nextDouble();
				if (mat[i][j] > 0.0) {
					positiveSum += mat[i][j];
				}
			}
		}
		
		System.out.printf("\nSOMA DOS POSITIVOS: %.1f\n", positiveSum);
		System.out.print("\nEscolha uma linha: ");
		int line = sc.nextInt();
		
		System.out.print("LINHA ESCOLHIDA: ");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == mat[line][j]) {
					System.out.printf("%.1f ", mat[line][j]);
				}
			}
		}
		
		System.out.println();
		System.out.print("\nEscolha uma coluna: ");
		int column = sc.nextInt();
		
		System.out.print("COLUNA ESCOLHIDA: ");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] == mat[i][column]) {
					System.out.printf("%.1f ", mat[i][column]);
				}
			}
		}
		
		System.out.println();
		System.out.print("\nDIAGONAL PRINCIPAL: ");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (i == j) {
					System.out.printf("%.1f ", mat[i][j]);
				}
			}
		}
		
		System.out.println();
		System.out.println("\nMATRIZ ALTERADA: ");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				if (mat[i][j] < 0.0) {
					mat[i][j] = Math.pow(mat[i][j], 2);
					System.out.printf("%.1f ", mat[i][j]);
				} else {
					System.out.printf("%.1f ", mat[i][j]);
				}
			}
			System.out.println();
		}
		
		sc.close();
	}

}
