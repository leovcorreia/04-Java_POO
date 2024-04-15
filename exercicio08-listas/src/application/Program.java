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
			while (hasId(list, id)) {
				System.out.println("Id already taken! Try again: ");
				id = sc.nextInt();
			}
			sc.nextLine(); // Limpeza buffer
			
			System.out.printf("Name: ");
			String name = sc.nextLine();
			
			System.out.printf("Salary: ");
			double salary = sc.nextDouble();
			sc.nextLine(); // Limpeza buffer
			
			Employees employee = new Employees(id, name, salary);
			
			list.add(i, employee);
		}
		
		System.out.printf("\nEnter the employee id that will have salary increase: ");
		int idIncrease = sc.nextInt();
		sc.nextLine(); // Limpeza buffer
		
		boolean result = false;
		for (Employees emp: list) {
			if (emp.getId() == idIncrease) {
				System.out.printf("Enter the percentage: ");
				double percentage = sc.nextDouble();
				sc.nextLine(); // Limpeza buffer
				
				emp.increaseSalary(percentage);
				result = true;
			}
		}
		
		if (!result) {
			System.out.println("This id does not exist! ");
		}
	
		System.out.println("\nList of employees: ");
		
		for (Employees emp: list) {
			System.out.printf("%d, %s, %.2f \n", emp.getId(), emp.getName(), emp.getSalary());
		}
		
		sc.close();
	}
	
	public static boolean hasId(List<Employees> list, int id) {
		Employees emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null;
	}

}
