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
		int age;
		double height;
		
		sc.nextLine(); // Limpeza buffer
		
		Person[] vect = new Person[n];
		double sum = 0.0;
		int count = 0;
		
		for (int i = 0; i < vect.length; i++) {
			System.out.printf("Dados da %da pessoa: \n", i+1);
			
			System.out.println("Nome: ");
			String name = sc.nextLine();
			
			System.out.println("Idade: ");
			age = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			
			System.out.println("Altura: ");
			height = sc.nextDouble();
				
			vect[i] = new Person(name, age, height);
			
			if (vect[i].getAge() < 16) {
				count += 1;
			}
			
			sum += vect[i].getHeight();
			sc.nextLine(); // Limpeza buffer
		}
		
		double avgHeight = sum / vect.length;
		System.out.printf("\nAltura media: %.2f\n", avgHeight);
		
		double less16 = (double) count / vect.length * 100;
		System.out.printf("Pessoas com menos de 16 anos: %.1f %% \n", less16);
		
		for (int i = 0; i < vect.length; i++) {
			if (vect[i].getAge() < 16) {
				System.out.printf("%s\n", vect[i].getName());
			}
		}
		
		sc.close();
	}

}
