package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
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
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2]
						, Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));				
				line = br.readLine();
			}
			
			Comparator<Double> comp = (x, y) -> x.averagePrice.compareTo(y.averagePrice());
			
			List<String> sales2016 = list.stream()
								.filter(x -> x.getYear() == 2016)
								.map(x -> x.averagePrice())
								.sorted(comp.reversed())
								.limit(5)
								.collect(Collectors.toList());
			
			System.out.println("Cinco primeiras vendas de 2016 de maior preço médio: ");
			sales2016.forEach(System.out::println);
								
			
		} catch(IOException e) {
			System.out.println("Erro: " + e);
		}
		
		sc.close();

	}

}
