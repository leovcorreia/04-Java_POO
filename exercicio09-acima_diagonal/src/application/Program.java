package application;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Qual a ordem da matriz? ");
		int n = sc.nextInt();
		
		int[][] mat = new int[n][n];
		int sum = 0;
		
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print("Elemento [" + i + "," + j + "]: ");
				mat[i][j] = sc.nextInt();
				if (j > i) {
					sum += mat[i][j];
				}
			}
		}
		
		System.out.printf("SOMA DOS ELEMENTOS DA DIAGONAL PRINCIPAL: %d", sum);
		
		sc.close();
	}

}
