package aplication;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		int i, qtdMulheres, qtdHomens, qtdAtletas;
		double maiorAltura, altura, peso, somaPeso, pesoMedio, porcHomens, somaAlturaMulheres, alturaMediaMulheres;
		String nome, sexo, maisAlto;
		
		System.out.println("Qual a quantidade de atletas?");
		qtdAtletas = sc.nextInt();
		sc.nextLine(); //limpeza buffer
		
		maisAlto = "";
		somaAlturaMulheres = 0.0;
		somaPeso = 0.0;
		maiorAltura = 0.0;
		qtdHomens = 0;
		qtdMulheres = 0;
		for (i = 1; i <= qtdAtletas; i++) {
			System.out.println("Digite os dados do atleta numero " + i + ":");
			System.out.println("Nome: ");
			nome = sc.nextLine();
			
			System.out.println("Sexo: ");
			sexo = sc.nextLine();
			while (!sexo.equals("F") && (!sexo.equals("M")) && (!sexo.equals("m")) && (!sexo.equals("f"))) {
				System.out.println("Valor invalido! Favor digitar F ou M: ");
				sexo = sc.nextLine();
			}
			if (sexo.equals("M") || (sexo.equals("m"))) {
				qtdHomens += 1;
			}
			
			System.out.println("Altura: ");
			altura = sc.nextDouble();
			while (altura <= 0.0) {
				System.out.println("Valor invalido! Favor digitar um valor positivo: ");
				altura = sc.nextDouble();
			}
			if (altura > maiorAltura) {
				maiorAltura = altura;
				maisAlto = nome;
			}
			if (sexo.equals("F") || (sexo.equals("f"))) {
				qtdMulheres += 1;
				somaAlturaMulheres += altura;
			}
			
			System.out.println("Peso: ");
			peso = sc.nextDouble();
			while (peso <= 0.0) {
				System.out.println("Valor invalido! Favor digitar um valor positivo: ");
				peso = sc.nextDouble();
			}
			sc.nextLine(); //limpeza buffer
			somaPeso += peso;
		}
		
		pesoMedio = somaPeso / qtdAtletas;
		porcHomens = (double) qtdHomens / qtdAtletas * 100.0;
		alturaMediaMulheres = somaAlturaMulheres / qtdMulheres;
		
		System.out.println("\nRELATORIO: ");
		System.out.printf("Peso medio dos atletas: %.2fkg\n", pesoMedio);
		System.out.printf("Atleta mais alto: %s\n", maisAlto);
		System.out.printf("Porcentagem de homens: %.1f %%\n", porcHomens);
		
		if (qtdMulheres > 0) {
			System.out.printf("Altura média das mulheres: %.2f", alturaMediaMulheres);
		} else {
			System.out.println("Não há mulheres cadastradas. ");
		}
			
		sc.close();
	}

}
