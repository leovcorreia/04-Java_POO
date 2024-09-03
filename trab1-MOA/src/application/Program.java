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
            int numIteracoes = 100; // Número de iterações do GRASP
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

            imprimirSolucao(melhorSolucao, arrayItens, capacidadesMochila, valorMaximo);

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
        int numItens = itens.length;  // Obtém o número total de itens.
        int numMochilas = capacidadesMochila.length;  // Obtém o número total de mochilas.
        int[] solucao = new int[numItens * numMochilas];  // Cria um vetor para armazenar a solução.

        for (int i = 0; i < numItens; i++) {  // Itera sobre cada item.
            for (int j = 0; j < numMochilas; j++) {  // Itera sobre cada mochila.
                if (capacidadesMochila[j] >= itens[i].getWeight()) {  // Verifica se a mochila tem capacidade suficiente para o item.
                    solucao[i + j * numItens] = 1;  // Aloca o item na mochila (marcando como 1 na matriz de solução).
                    capacidadesMochila[j] -= itens[i].getWeight();  // Subtrai o peso do item da capacidade da mochila.
                    break;  // Sai do loop das mochilas, pois o item já foi alocado.
                }
            }
        }

        return solucao;  // Retorna a solução construída.
    }


    private static int[] buscaLocal(int[] solucao, Item[] itens, int[] capacidadesMochila) {
        boolean melhoriaEncontrada;  // Variável para verificar se alguma melhoria foi encontrada.
        int numItens = itens.length;  // Obtém o número total de itens.
        int numMochilas = capacidadesMochila.length;  // Obtém o número total de mochilas.

        do {
            melhoriaEncontrada = false;  // Inicializa sem melhorias encontradas.

            for (int i = 0; i < numItens; i++) {  // Itera sobre cada item.
                int mochilaAtual = -1;  // Inicializa a variável para rastrear a mochila atual do item.

                for (int j = 0; j < numMochilas; j++) {  // Encontra em qual mochila o item está atualmente alocado.
                    if (solucao[i + j * numItens] == 1) {
                        mochilaAtual = j;  // Aloca a posição da mochila do item.
                        break;
                    }
                }

                if (mochilaAtual == -1) continue;  // Se o item não estiver em nenhuma mochila, continue para o próximo item.

                for (int j = 0; j < numMochilas; j++) {  // Tenta mover o item para uma nova mochila.
                    if (j != mochilaAtual && capacidadesMochila[j] >= itens[i].getWeight()) {
                        int valorAtual = calcularValor(solucao, itens);  // Calcula o valor atual da solução.

                        // Move o item para a nova mochila temporariamente.
                        solucao[i + mochilaAtual * numItens] = 0;
                        solucao[i + j * numItens] = 1;
                        capacidadesMochila[mochilaAtual] += itens[i].getWeight();
                        capacidadesMochila[j] -= itens[i].getWeight();

                        int novoValor = calcularValor(solucao, itens);  // Calcula o novo valor da solução.

                        // Verifica se o movimento realmente melhora a solução.
                        if (novoValor > valorAtual) {
                            melhoriaEncontrada = true;  // Marca que uma melhoria foi encontrada.
                            System.out.println("Movendo item " + i + " da mochila " + mochilaAtual + " para a mochila " + j);
                            break;
                        } else {
                            // Reverte o movimento se não melhorar a solução.
                            solucao[i + j * numItens] = 0;
                            solucao[i + mochilaAtual * numItens] = 1;
                            capacidadesMochila[mochilaAtual] -= itens[i].getWeight();
                            capacidadesMochila[j] += itens[i].getWeight();
                        }
                    }
                }

                if (melhoriaEncontrada) {
                    break;  // Se uma melhoria foi encontrada, sai do loop e inicia uma nova busca.
                }
            }

        } while (melhoriaEncontrada);  // Continua até que nenhuma melhoria adicional seja encontrada.

        return solucao;  // Retorna a solução melhorada.
    }


    private static int calcularValor(int[] solucao, Item[] itens) {
        int valorTotal = 0;  // Inicializa o valor total.

        for (int i = 0; i < solucao.length; i++) {  // Itera sobre a solução.
            if (solucao[i] == 1) {  // Se o item estiver alocado (valor 1 na matriz de solução).
                int itemIndex = i % itens.length;  // Calcula o índice do item correspondente.
                valorTotal += itens[itemIndex].getValue();  // Soma o valor do item ao valor total.
            }
        }

        return valorTotal;  // Retorna o valor total da solução.
    }


    private static void imprimirSolucao(int[] solucao, Item[] itens, int[] capacidadesIniciais, int valorTotal) {
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
                System.out.println("** Erro: A capacidade restante não pode ser negativa. Verifique o algoritmo **");
            }

            System.out.println("  Soma dos Pesos: " + somaPesos);
            System.out.println("  Soma dos Valores: " + somaValores);
            System.out.println("  Capacidade Restante após alocação: " + capacidadeRestante);
            System.out.println();
        }
        System.out.println("Valor Total da Solução: " + valorTotal);
    }
}