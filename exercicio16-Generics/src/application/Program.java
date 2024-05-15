package application;

import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import entities.Student;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Set<Student> set = new TreeSet<>();

		System.out.print("How many students for course A? ");
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int code = sc.nextInt();
			Student st = new Student(code);
			set.add(st);
		}
		
		System.out.print("How many students for course B? ");
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int code = sc.nextInt();
			Student st = new Student(code);
			set.add(st);
		}
		
		System.out.print("How many students for course C? ");
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int code = sc.nextInt();
			Student st = new Student(code);
			set.add(st);
		}
		
		System.out.println("Total students: " + set.size());
		
		sc.close();
	}

}
