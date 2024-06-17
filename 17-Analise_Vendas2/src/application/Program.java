package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			List<Sale> list = new ArrayList<>();
			Set<String> sellersName = new TreeSet<>();
			
			String line = br.readLine();
			while(line != null) {
				
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), 
						fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				
				sellersName.add(fields[2]);
				
				line = br.readLine();
			}
			
			System.out.println("\nTotal de vendas por vendedor: ");
			
			Map<String, Double> totalSalesBySeller = sellersName.stream()
	                .collect(Collectors.toMap(
	                    seller -> seller,
	                    seller -> list.stream()
	                                  .filter(sale -> sale.getSeller().equals(seller))
	                                  .mapToDouble(Sale::getTotal)
	                                  .sum()
	                ));
			
			totalSalesBySeller.forEach((seller, total) -> 
            System.out.println(seller + " - R$" + String.format("%.2f", total)));
				
		} catch(IOException e) {
			System.out.println("Erro: " + e);
		}
		
		sc.close();

	}

}
