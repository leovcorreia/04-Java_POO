package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employees;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many employees will be registered? ");
		int n = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		List<Employees> list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			System.out.printf("\nEmployee #%d: \n", i+1);
			
			System.out.printf("Id: ");
			int id = sc.nextInt();
			sc.nextLine(); // Limpeza buffer
			
			System.out.printf("Name: ");
			String name = sc.nextLine();
			
			System.out.printf("Salary: ");
			double salary = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			Employees employee = new Employees(id, name, salary);
			
			list.add(i, employee);
		}
		
		for (Employees emp: list) {
			System.out.printf("Id do funcionario: %d\n", emp.getId());
			System.out.printf("Nome do funcionario: %s\n", emp.getName());
			System.out.printf("Salario do funcionario: %.2f\n", emp.getSalary());
		}
		
		sc.close();
	}

}
