package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		Double salaryToBeComputed = sc.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Employee> emp = new ArrayList<>();
			
			// READING THE FILE
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				
				line = br.readLine();
			}
			
			List<String> emails = emp.stream()
								.filter(x -> x.getSalary() > salaryToBeComputed)
								.map(x -> x.getEmail())
								.sorted()
								.collect(Collectors.toList());
			
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salaryToBeComputed) + ":");
			emails.forEach(System.out::println);
			
			double sum = emp.stream()
						.filter(x -> x.getName().charAt(0) == 'M')
						.map(x -> x.getSalary())
						.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
			
		} catch(RuntimeException e) {
			System.out.println("Unexpected error: " + e);
		} catch(IOException e) {
			System.out.println("Error: " + e);
		}
		
		sc.close();

	}

}
