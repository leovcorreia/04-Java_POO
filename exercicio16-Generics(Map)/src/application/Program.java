package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		Map<String, Integer> elections = new TreeMap<>();
		
		System.out.println("Enter file full path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			
			while (line != null) {
				String[] fields = line.split(",");
				String name = fields[0];
				Integer votes = Integer.parseInt(fields[1]);
				
				if (elections.containsKey(name)) {
					elections.put(name, elections.get(name) + votes);
				} else {
					elections.put(name, votes);
				}
				
				line = br.readLine();
			}
			
			for (String key: elections.keySet()) {
				System.out.println(key + ": " + elections.get(key));
			}
			
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		catch (RuntimeException e) {
			System.out.println("RunTimeExceptionError: " + e.getMessage());
		}
		
		sc.close();
	}

}
