package application;

import java.util.Locale;
import java.util.Scanner;

import entities.Address;
import entities.Department;
import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Nome do departamento: ");
		String deptName = sc.nextLine();
		System.out.print("Dia do pagamento: ");
		int deptPayDay = sc.nextInt();
		sc.nextLine(); // LImpeza buffer
		System.out.print("Email: ");
		String deptEmail = sc.nextLine();
		System.out.print("Telefone: ");
		String deptPhone = sc.nextLine();
		
		Address address = new Address(deptEmail, deptPhone);
		Department dept = new Department(deptName, deptPayDay, address);
		
		System.out.print("Quantos funcionários tem o departamento? ");
		int n = sc.nextInt();
		sc.nextLine(); // LImpeza buffer
		
		for (int i=0; i < n; i++) {
			System.out.println("Dados do funcionario " + (i+1) + ": ");
			System.out.print("Nome: ");
			String empName = sc.nextLine();
			System.out.print("Salário: ");
			Double empSalary = sc.nextDouble();
			sc.nextLine(); // LImpeza buffer
			
			Employee emp = new Employee(empName, empSalary);
			dept.addEmployee(emp);
		}
		
		showReport(dept);
		
		sc.close();
	}
	
	public static void showReport(Department dept) {
		System.out.println("\nFOLHA DE PAGAMENTO: ");
		System.out.println("Departamento " + dept.getName() + " = R$ " + String.format("%.2f", dept.payroll()));
		System.out.println("Pagamento realizado no dia " + dept.getPayDay());
		System.out.println("Funcionários: ");
		for (Employee emp: dept.getEmployees()) {
			System.out.println(emp.getName());
		}
		System.out.println("Para dúvidas favor entrar em contato: " + dept.getAddress().getEmail());
	}

}
