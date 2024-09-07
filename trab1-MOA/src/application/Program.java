package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
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

            // Parâmetros do GRASP
            int numIteracoes = 10000; // Número de iterações do GRASP
            int valorMaximo = 0;
            int[] melhorSolucao = null;

            // Início do tempo de execução do GRASP
            long startGraspTime = System.nanoTime();

            for (int it = 0; it < numIteracoes; it++) {
                System.out.println("Iteração " + (it + 1) + " de " + numIteracoes);
                int[] solucao = construirSolucaoGulosaAleatoria(arrayItens, capacidadesMochila.clone());
                System.out.println("Solução inicial construída.");
                solucao = buscaLocal(solucao, arrayItens, capacidadesMochila.clone());
                int valorAtual = calcularValor(solucao, arrayItens);

                if (valorAtual > valorMaximo) {
                    valorMaximo = valorAtual;
                    melhorSolucao = solucao.clone();
                }
            }

            long endGraspTime = System.nanoTime();
            long graspTime = endGraspTime - startGraspTime;

            imprimirSolucaoDetalhada(melhorSolucao, arrayItens, capacidadesMochila, valorMaximo);

            System.out.println("=====================================");
            System.out.println("Relatório de Desempenho do GRASP:");
            System.out.println("Tempo total de execução do GRASP = " + String.format("%.2f", (graspTime / 1_000_000_000.0)) + " segundos");
            System.out.println("=====================================");

        } catch (IOException e) {
            System.out.println("Erro: " + e);
        }

        sc.close();
    }

    
    private static int[] construirSolucaoGulosaAleatoria(Item[] itens, int[] capacidadesMochila) {
        int numItens = itens.length;
        int numMochilas = capacidadesMochila.length;
        int[] solucao = new int[numItens * numMochilas];
        Random random = new Random();

        for (int i = 0; i < numItens; i++) {
            ArrayList<Integer> rcl = new ArrayList<>(); // Lista Restrita de Candidatos

            // Construir a RCL com mochilas que têm capacidade suficiente
            for (int j = 0; j < numMochilas; j++) {
                if (capacidadesMochila[j] >= itens[i].getWeight()) {
                    rcl.add(j); // Adiciona a mochila à RCL se ela tiver capacidade suficiente
                }
            }

            // Seleciona uma mochila aleatoriamente da RCL, se houver mochilas disponíveis
            if (!rcl.isEmpty()) {
                int mochilaEscolhida = rcl.get(random.nextInt(rcl.size())); // Escolhe aleatoriamente uma mochila da RCL
                solucao[i + mochilaEscolhida * numItens] = 1;
                capacidadesMochila[mochilaEscolhida] -= itens[i].getWeight();
            }
        }

        return solucao;
    }

    private static int[] buscaLocal(int[] solucao, Item[] itens, int[] capacidadesMochila) {
        // Declaração de uma variável para indicar se uma melhoria foi encontrada durante a busca local
        boolean melhoriaEncontrada;

        // Número total de itens e mochilas
        int numItens = itens.length;
        int numMochilas = capacidadesMochila.length;

        // Laço principal da busca local, que continua até que nenhuma melhoria seja encontrada
        do {
            melhoriaEncontrada = false; // Inicia cada iteração assumindo que nenhuma melhoria foi encontrada

            // Loop através de cada item para tentar realocá-lo
            for (int i = 0; i < numItens; i++) {
                int mochilaAtual = -1; // Variável para armazenar a mochila atual onde o item está alocado

                // Loop para encontrar em qual mochila o item i está alocado atualmente
                for (int j = 0; j < numMochilas; j++) {
                    if (solucao[i + j * numItens] == 1) { // Verifica se o item i está alocado na mochila j
                        mochilaAtual = j; // Se o item está alocado na mochila j, guarda o índice de j
                        break; // Interrompe o loop porque já encontrou a mochila atual do item
                    }
                }

                // Se o item i não estiver alocado em nenhuma mochila, passa para o próximo item
                if (mochilaAtual == -1) continue;

                // Loop para verificar em qual outra mochila o item i pode ser realocado
                for (int j = 0; j < numMochilas; j++) {
                    // Verifica se j não é a mochila atual e se a mochila j tem capacidade suficiente
                    if (j != mochilaAtual && capacidadesMochila[j] >= itens[i].getWeight()) {
                        // Calcula o valor total da solução antes da realocação do item
                        int valorAtual = calcularValor(solucao, itens);

                        // Move o item i da mochila atual para a mochila j temporariamente
                        solucao[i + mochilaAtual * numItens] = 0; // Remove o item da mochila atual
                        solucao[i + j * numItens] = 1; // Aloca o item na nova mochila
                        capacidadesMochila[mochilaAtual] += itens[i].getWeight(); // Atualiza a capacidade da mochila atual
                        capacidadesMochila[j] -= itens[i].getWeight(); // Atualiza a capacidade da nova mochila

                        // Calcula o valor total da solução após o movimento
                        int novoValor = calcularValor(solucao, itens);

                        // Se o movimento melhorou a solução, mantém a nova alocação
                        if (novoValor > valorAtual) {
                            melhoriaEncontrada = true; // Marca que uma melhoria foi encontrada
                            System.out.println("Movendo item " + i + " da mochila " + mochilaAtual + " para a mochila " + j);
                            break; // Interrompe o loop porque uma melhoria foi encontrada
                        } else {
                            // Se não houver melhoria, reverte a alocação para o estado anterior
                            solucao[i + j * numItens] = 0; // Remove o item da nova mochila
                            solucao[i + mochilaAtual * numItens] = 1; // Recoloca o item na mochila original
                            capacidadesMochila[mochilaAtual] -= itens[i].getWeight(); // Reverte a capacidade da mochila atual
                            capacidadesMochila[j] += itens[i].getWeight(); // Reverte a capacidade da nova mochila
                        }
                    }
                }

                // Se uma melhoria foi encontrada, interrompe o loop para verificar novamente a solução
                if (melhoriaEncontrada) {
                    break;
                }
            }

        // Continua o processo até que nenhuma melhoria seja encontrada
        } while (melhoriaEncontrada);

        // Retorna a solução final após a busca local
        return solucao;
    }


    private static int calcularValor(int[] solucao, Item[] itens) {
        int valorTotal = 0;
        for (int i = 0; i < solucao.length; i++) {
            if (solucao[i] == 1) {
                int itemIndex = i % itens.length;
                valorTotal += itens[itemIndex].getValue();
            }
        }
        return valorTotal;
    }

    private static void imprimirSolucaoDetalhada(int[] solucao, Item[] itens, int[] capacidadesIniciais, int valorTotal) {
        int numMochilas = capacidadesIniciais.length;
        int numItens = itens.length;

        System.out.println("Solução Final Detalhada:");
        for (int j = 0; j < numMochilas; j++) {
            int somaPesos = 0;
            int somaValores = 0;
            System.out.println("Mochila " + j + " (Capacidade Inicial: " + capacidadesIniciais[j] + ")");
            for (int i = 0; i < numItens; i++) {
                if (solucao[i + j * numItens] == 1) {
                    somaPesos += itens[i].getWeight();
                    somaValores += itens[i].getValue();
                    System.out.println("  Item " + i + " - Peso: " + itens[i].getWeight() + ", Valor: " + itens[i].getValue());
                }
            }
            int capacidadeRestante = capacidadesIniciais[j] - somaPesos;

            if (capacidadeRestante < 0) {
                System.out.println("** Erro: A capacidade restante não pode ser negativa. **");
            }

            System.out.println("  Soma dos Pesos: " + somaPesos);
            System.out.println("  Soma dos Valores: " + somaValores);
            System.out.println("  Capacidade Restante após alocação: " + capacidadeRestante);
            System.out.println();
        }
        System.out.println("Valor Total da Solução: " + valorTotal);
    }
}