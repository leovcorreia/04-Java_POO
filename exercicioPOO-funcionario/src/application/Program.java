package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Employee employee = new Employee();
		
		System.out.println("Name: ");
		employee.name = sc.nextLine();
		System.out.println("Gross salary: ");
		employee.grossSalary = sc.nextDouble();
		System.out.println("Tax: ");
		employee.tax = sc.nextDouble();
		
		// System.out.printf("Funcionario: " + employee.name + " , $ " + String.format("%.2f", employee.netSalary()));
		System.out.println("\nEmployee: \n" + employee);
		
		System.out.println("\nWhat is the percentage to increase the salary? ");
		double percentage = sc.nextDouble();
		employee.increaseSalary(percentage);

		System.out.printf("\nUpdated employee data: \n%s", employee);
		
		sc.close();
	}
}
