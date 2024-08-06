package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import entities.Item;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre com o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			
			String line = br.readLine();
			// LÊ A QUANTIDADE DE MOCHILAS
			int qtdMochilas = Integer.parseInt(line);
			int[] capacidadesMochila = new int[qtdMochilas];
				
			// LÊ A QUANTIDADE DE ITENS
			line = br.readLine();
			int qtdItens = Integer.parseInt(line);
				
			// LÊ A CAPACIDADE DE CADA MOCHILA E GUARDA EM UM VETOR
			for (int i = 0; i < qtdMochilas; i++) {
				line = br.readLine();
				capacidadesMochila[i] = Integer.parseInt(line);
			}
				
			// LÊ PESO E VALOR DE CADA ITEM, CRIA O OBJETO ITEM E GUARDA EM UM VETOR DO TIPO ITEM
			Item[] arrayItens = new Item[qtdItens];
			for (int i = 0; i < qtdItens; i++) {
				line = br.readLine();
				String[] fields = line.split("\\s+");
					
				int peso = Integer.parseInt(fields[0]);
				int valor = Integer.parseInt(fields[1]);
				arrayItens[i] = new Item(peso, valor);
			}
				
			int[][] matStatus = new int[qtdItens][qtdMochilas]; 
			int funcObjetivo = funcaoObjetivo(arrayItens, qtdItens, qtdMochilas, matStatus);
			
		} catch (IOException e) {
			System.out.println("Erro: " + e);
		}
		
		sc.close();
	}
	
	public static int funcaoObjetivo(Item[] arrayItens, int qtdItens, int qtdMochilas, int[][] matStatus) {
		for (int i = 0; i < ; i++) {
			for (int j = 0; j < ; j++) {
				
			}
		}
	}

}
