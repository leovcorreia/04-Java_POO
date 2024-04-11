package application;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US); // Tem que vir primeiro que o Scanner p/ funcionar certo!
		Scanner sc = new Scanner(System.in);
		
		int n;
		System.out.println("Quantos alunos serao digitados? ");
		n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		String[] nomes = new String[n];
		double[] notas1 = new double[n];
		double[] notas2 = new double[n];
		
		for (int i = 0; i < nomes.length; i++) {
			System.out.printf("Digite nome, primeira e segunta nota do %do aluno:\n", i+1);
			System.out.printf("Digite o nome do aluno: ");
			nomes[i] = sc.nextLine();
			
			System.out.printf("Digite a primeira nota do aluno: ");
			notas1[i] = sc.nextDouble();
			
			System.out.printf("Digite a segunda nota do aluno: ");
			notas2[i] = sc.nextDouble();
			
			sc.nextLine(); // Limpeza buffer
		}
		
		System.out.println("Alunos aprovados: ");
		
		for (int i = 0; i < nomes.length; i++) {
			double media = (notas1[i] + notas2[i]) / 2.0;
			if (media >= 6.0) {
				System.out.printf("%s\n", nomes[i]);
			}
		}
		
		sc.close();
	}

}
