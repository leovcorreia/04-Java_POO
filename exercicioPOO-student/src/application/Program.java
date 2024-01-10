package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Student;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Student st = new Student();
		
		System.out.println("Digite o nome do aluno: ");
		st.name = sc.nextLine();
		System.out.println("Digite a nota do 1º trimestre: ");
		st.grade1 = sc.nextDouble();
		System.out.println("Digite a nota do 2º trimestre: ");
		st.grade2 = sc.nextDouble();
		System.out.println("Digite a nota do 3º trimestre: ");
		st.grade3 = sc.nextDouble();
		
		System.out.println(st);
		
		if (st.result()) {
			System.out.println("PASS");
		} else {
			System.out.println("FAILED");
			System.out.printf("FALTARAM %.2f PONTOS PARA PASSAR", st.missingPoints());
		}
		
		sc.close();
	}

}
