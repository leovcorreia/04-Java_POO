package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Person;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Quantas pessoas serao digitadas? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		Person[] vect = new Person[n];
		double sum = 0.0;
		int count = 0;
		
		for (int i = 0; i < vect.length; i++) {
			System.out.printf("Dados da %da pessoa: \n", i+1);
			
			System.out.println("Nome: ");
			String name = sc.nextLine();
			
			System.out.println("Idade: ");
			int age = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			
			System.out.println("Altura: ");
			double height = sc.nextDouble();
				
			vect[i] = new Person(name, age, height);
			
			if (vect[i].getAge() < 16) {
				count += 1;
			}
			
			sum += vect[i].getHeight();
		}
		
		double avgHeight = sum / vect.length;
		System.out.printf("\nAltura media: %.2f", avgHeight);
		
		double less16 = count / vect.length;
		System.out.printf("Pessoas com menos de 16 anos: %.1f%", less16);
		
		for (int i = 0; i < vect.length; i++) {
			if (vect[i].getAge() < 16) {
				System.out.println("%s\n" + vect[i].getName());
			}
		}
		
		sc.close();
	}

}
