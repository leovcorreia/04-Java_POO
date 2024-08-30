package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
            int numIteracoes = 10000; // Aumentar as iterações
            int valorMaximo = 0;
            int[] melhorSolucao = null;

            // Início do tempo de execução do GRASP
            long startGraspTime = System.nanoTime();

            for (int it = 0; it < numIteracoes; it++) {
                System.out.println("Iteração " + (it + 1) + " de " + numIteracoes);
                int[] solucao = construirSolucaoGulosaAleatoria(arrayItens, capacidadesMochila.clone());
                System.out.println("Solução inicial construída.");

                solucao = buscaLocalIntensificada(solucao, arrayItens, capacidadesMochila.clone());

                int valorAtual = calcularValor(solucao, arrayItens);

                if (valorAtual > valorMaximo) {
                    valorMaximo = valorAtual;
                    melhorSolucao = solucao.clone();
                    System.out.println("Nova melhor solução encontrada com valor: " + valorMaximo);
                }
            }

            long endGraspTime = System.nanoTime();
            long graspTime = endGraspTime - startGraspTime;

            imprimirSolucaoDetalhada(melhorSolucao, arrayItens, capacidadesMochila, valorMaximo);
            imprimirSolucaoGeral(melhorSolucao, arrayItens, capacidadesMochila.length);

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
            double melhorRazao = Double.NEGATIVE_INFINITY;
            int melhorMochila = -1;

            // Alterna entre diferentes critérios de seleção
            for (int j = 0; j < numMochilas; j++) {
                if (capacidadesMochila[j] >= itens[i].getWeight()) {
                    double razao = (double) itens[i].getValue() / itens[i].getWeight();
                    if (random.nextBoolean()) {
                        razao = itens[i].getValue(); // Critério alternativo
                    }
                    if (razao > melhorRazao || (razao == melhorRazao && random.nextBoolean())) {
                        melhorRazao = razao;
                        melhorMochila = j;
                    }
                }
            }

            if (melhorMochila != -1) {
                solucao[i + melhorMochila * numItens] = 1;
                capacidadesMochila[melhorMochila] -= itens[i].getWeight();
            }
        }

        return solucao;
    }

    private static int[] buscaLocalIntensificada(int[] solucao, Item[] itens, int[] capacidadesMochila) {
        boolean melhoriaEncontrada;
        int numItens = itens.length;
        int numMochilas = capacidadesMochila.length;
        int maxIteracoesSemMelhoria = 1000;
        int iteracoesSemMelhoria = 0;

        do {
            melhoriaEncontrada = false;

            for (int i = 0; i < numItens; i++) {
                int mochilaAtual = -1;
                for (int j = 0; j < numMochilas; j++) {
                    if (solucao[i + j * numItens] == 1) {
                        mochilaAtual = j;
                        break;
                    }
                }

                if (mochilaAtual == -1) continue;

                for (int j = 0; j < numMochilas; j++) {
                    if (j != mochilaAtual && capacidadesMochila[j] >= itens[i].getWeight()) {
                        int valorAtual = calcularValor(solucao, itens);

                        // Tentar mover o item para a nova mochila sem violar a capacidade
                        solucao[i + mochilaAtual * numItens] = 0;
                        solucao[i + j * numItens] = 1;
                        capacidadesMochila[mochilaAtual] += itens[i].getWeight();
                        capacidadesMochila[j] -= itens[i].getWeight();

                        if (capacidadesMochila[j] < 0) { // Verificação adicional
                            // Capacidade violada, reverte o movimento
                            solucao[i + j * numItens] = 0;
                            solucao[i + mochilaAtual * numItens] = 1;
                            capacidadesMochila[mochilaAtual] -= itens[i].getWeight();
                            capacidadesMochila[j] += itens[i].getWeight();
                        } else {
                            int novoValor = calcularValor(solucao, itens);

                            if (novoValor > valorAtual) {
                                melhoriaEncontrada = true;
                                iteracoesSemMelhoria = 0;
                                System.out.println("Movendo item " + i + " da mochila " + mochilaAtual + " para a mochila " + j);
                                break;
                            } else {
                                // Reverte o movimento se não melhorar a solução
                                solucao[i + j * numItens] = 0;
                                solucao[i + mochilaAtual * numItens] = 1;
                                capacidadesMochila[mochilaAtual] -= itens[i].getWeight();
                                capacidadesMochila[j] += itens[i].getWeight();
                            }
                        }
                    }
                }

                if (melhoriaEncontrada) {
                    break;
                }
            }

            iteracoesSemMelhoria++;
            if (iteracoesSemMelhoria >= maxIteracoesSemMelhoria) {
                break;
            }

        } while (melhoriaEncontrada);

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

        System.out.println("Solução Final:");
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
    
    private static void imprimirSolucaoGeral(int[] solucao, Item[] itens, int numMochilas) {
        int numItens = itens.length;

        System.out.println("Solução Geral:");
        for (int i = 0; i < numItens; i++) {
            for (int j = 0; j < numMochilas; j++) {
                if (solucao[i + j * numItens] == 1) {
                    System.out.println("Item " + i + " alocado na Mochila " + j + " (x" + i + "_" + j + " = 1)");
                }
            }
        }
    }

}
