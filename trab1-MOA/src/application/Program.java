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
			
			// MATRIZ QUE TERÁ 1 CASO ITEM I ESTIVER NA MOCHILA J; TERÁ 0, CASO CONTRÁRIO
			int[][] matStatus = new int[qtdItens][qtdMochilas]; 
			
			String funcObjetivo = montarFuncaoObjetivo(arrayItens, qtdItens, qtdMochilas);
			System.out.println("Função objetivo: " + funcObjetivo);
			
		} catch (IOException e) {
			System.out.println("Erro: " + e);
		}
		
		sc.close();
	}
	
	public static String montarFuncaoObjetivo(Item[] arrayItens, int qtdItens, int qtdMochilas) {
		StringBuilder funcObjetivo = new StringBuilder("maximize: ");

        for (int i = 0; i < qtdItens; i++) {
            for (int j = 0; j < qtdMochilas; j++) {
                if (i > 0 || j > 0) {
                    funcObjetivo.append(" + ");
                }
                funcObjetivo.append(arrayItens[i].getValue())
                	.append(" * x")
                	.append(i)
                	.append("_")
                	.append(j);
                // Exemplo de como ficaria:
                // maximize: 10 * x0_0 + 10 * x0_1 + 15 * x1_0 + 15 * x1_1
            }
        }

        return funcObjetivo.toString();
	}
	
	public static String montarRestrições(Item[] arrayItens, int qtdItens, int qtdMochilas, int[] capacidadesMochilas) {
	    StringBuilder restricoesCapacidade = new StringBuilder();

	    // Itera sobre cada mochila
	    for (int j = 0; j < qtdMochilas; j++) {
	        // Adiciona a restrição para a mochila j
	        if (j > 0) {
	            restricoesCapacidade.append("\n");
	        }
	        restricoesCapacidade.append("s.a. mochila").append(j).append(": ");
	        
	        // Adiciona a soma dos pesos dos itens na mochila j
	        for (int i = 0; i < qtdItens; i++) {
	            if (i > 0) {
	                restricoesCapacidade.append(" + ");
	            }
	            restricoesCapacidade.append(arrayItens[i].getWeight()).append(" * x").append(i).append("_").append(j);
	        }
	        
	        // Adiciona a capacidade máxima da mochila
	        restricoesCapacidade.append(" <= ").append(capacidadesMochilas[j]);
	        
	        // Exemplo de comi ficaria:
	        // s.t. mochila0: 10 * x0_0 + 20 * x1_0 + 15 * x2_0 <= 50
	        // s.t. mochila1: 10 * x0_1 + 20 * x1_1 + 15 * x2_1 <= 70

	    }

	    return restricoesCapacidade.toString();
	}


}
