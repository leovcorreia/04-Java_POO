package application;

import scpsolver.constraints.LinearSmallerThanEqualsConstraint;
import scpsolver.lpsolver.LinearProgramSolver;
import scpsolver.lpsolver.SolverFactory;
import scpsolver.problems.LinearProgram;

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
        
        System.out.println("Entre com o caminho do arquivo de entrada: ");
        String path = sc.nextLine();
        
        // Leitura dos dados do arquivo de entrada/instância
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            
            String line = br.readLine();
            int qtdMochilas = Integer.parseInt(line);
            int[] capacidadesMochila = new int[qtdMochilas];
                
            line = br.readLine();
            int qtdItens = Integer.parseInt(line);
                
            for (int i = 0; i < qtdMochilas; i++) {
                line = br.readLine();
                capacidadesMochila[i] = Integer.parseInt(line);
            }
                
            Item[] arrayItens = new Item[qtdItens];
            for (int i = 0; i < qtdItens; i++) {
                line = br.readLine();
                String[] fields = line.split("\\s+");
                    
                int peso = Integer.parseInt(fields[0]);
                int valor = Integer.parseInt(fields[1]);
                arrayItens[i] = new Item(peso, valor);
            }
            
            // Criar o problema com SCPSolver
            int numVariaveis = qtdItens * qtdMochilas;
            double[] coeficientesObjetivo = new double[numVariaveis];
            for (int i = 0; i < qtdItens; i++) {
                for (int j = 0; j < qtdMochilas; j++) {
                    coeficientesObjetivo[i + j * qtdItens] = arrayItens[i].getValue();
                }
            }

            // Criar o LinearProgram com a função objetivo
            LinearProgram lp = new LinearProgram(coeficientesObjetivo);

            // Configurar o problema como de maximização
            lp.setMinProblem(false);  // Maximizar

            // Definir variáveis como binárias (inteiras 0 ou 1)
            for (int i = 0; i < numVariaveis; i++) {
                lp.setBinary(i);
            }

            // Adicionar restrições de capacidade
            for (int j = 0; j < qtdMochilas; j++) {
                double[] coeficientes = new double[numVariaveis];
                for (int i = 0; i < qtdItens; i++) {
                    coeficientes[i + j * qtdItens] = arrayItens[i].getWeight();
                }
                lp.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientes, capacidadesMochila[j], "c_mochila" + j));
            }

            // Adicionar restrições de unicidade
            for (int i = 0; i < qtdItens; i++) {
                double[] coeficientes = new double[numVariaveis];
                for (int j = 0; j < qtdMochilas; j++) {
                    coeficientes[i + j * qtdItens] = 1; // Garantir que cada item só possa estar em uma mochila
                }
                lp.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientes, 1, "c_unicidade" + i));
            }

            // Marca o início do tempo de execução do solver
            long startSolverTime = System.nanoTime();

            // Resolver o problema utilizando o solver padrão 
            LinearProgramSolver solver = SolverFactory.newDefault();  // Uso do solver padrão GLPK
            double[] sol = solver.solve(lp);

            // Marca o final do tempo de execução do solver
            long endSolverTime = System.nanoTime();

            // Calcula o tempo total de execução do solver
            long solverTime = endSolverTime - startSolverTime;

            int valorTotal = 0;
            
            System.out.println("=====================================");
            System.out.println("Solução Detalhada: ");
            // Verificar a solução e imprimir a soma dos pesos e valores em cada mochila
            for (int j = 0; j < qtdMochilas; j++) {
                int somaPesos = 0;
                int somaValores = 0;
                System.out.println("Mochila " + j + " - Capacidade: " + capacidadesMochila[j]);
                for (int i = 0; i < qtdItens; i++) {
                    if (sol[i + j * qtdItens] == 1.0) {
                        somaPesos += arrayItens[i].getWeight();
                        somaValores += arrayItens[i].getValue();
                        System.out.println("  Item " + i + " (Peso: " + arrayItens[i].getWeight() + ", Valor: " + arrayItens[i].getValue() + ")");
                    }
                }
                System.out.println("  Soma dos Pesos: " + somaPesos);
                System.out.println("  Soma dos Valores: " + somaValores);
                valorTotal += somaValores;
                if (somaPesos > capacidadesMochila[j]) {
                    System.out.println("** Violação da capacidade da mochila " + j + " **");
                }
            }
            
            System.out.println("=====================================");
         // Imprimir a solução onde os valores são iguais a 1
            System.out.println("Solução Geral: ");
            for (int j = 0; j < qtdMochilas; j++) {
                for (int i = 0; i < qtdItens; i++) {
                    if (sol[i + j * qtdItens] == 1.0) {
                        System.out.println("Item " + i + " alocado na Mochila " + j + " (x" + i + "_" + j + " = 1)");
                    }
                }
            }
            System.out.println("=====================================");
            
         // Exibir o valor total
            System.out.println("Valor total dos itens selecionados: " + valorTotal);
            
            // Exibir o relatório de desempenho do solver
            System.out.println("=====================================");
            System.out.println("Relatório de Desempenho do Solver:");
            System.out.println("Tempo total de execução do solver = " + String.format("%.2f", (solverTime / 1_000_000_000.0)) + " segundos");
            System.out.println("=====================================");

        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
        
        sc.close();
    }
}
