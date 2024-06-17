package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Sale> list = new ArrayList<>();
			Map<String, Double> sales = new TreeMap<>();
			
			String line = br.readLine();
			while(line != null) {
				
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), 
						fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				
				if (sales.containsKey(fields[2])) {
					sales.put(fields[2], sales.get(fields[2]) + Double.parseDouble(fields[4]));
				} else {
					sales.put(fields[2], Double.parseDouble(fields[4]));
				}
				
				line = br.readLine();
			}
			
			System.out.println();
			for (String key: sales.keySet()) {
				System.out.println(key + " - R$ " + String.format("%.2f", sales.get(key)));
			}
			
		} catch(IOException e) {
			System.out.println("Erro: " + e);
		}
		
		sc.close();

	}

}
