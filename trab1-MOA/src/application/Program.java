package application;

import scpsolver.constraints.LinearBiggerThanEqualsConstraint;
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
                    coeficientes[i + j * qtdItens] = 1;
                }
                lp.addConstraint(new LinearSmallerThanEqualsConstraint(coeficientes, 1, "c_unicidade" + i));
            }

            // Configurar o problema como de maximização
            lp.setMinProblem(false);  // Maximizar

            // Resolver o problema
            LinearProgramSolver solver = SolverFactory.newDefault();
            double[] sol = solver.solve(lp);

            // Exibir a solução
            System.out.println("Solução: ");
            for (int i = 0; i < sol.length; i++) {
                System.out.println("x" + (i / qtdMochilas) + "_" + (i % qtdMochilas) + " = " + sol[i]);
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }
        
        sc.close();
    }
}