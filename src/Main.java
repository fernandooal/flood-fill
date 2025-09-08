package src;

import src.floodfill.FloodFillStack;
import src.floodfill.FloodFillQueue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================");
        System.out.println("        ALGORITMO FLOOD FILL");
        System.out.println("===============================================");

        // Carregar imagem
        BufferedImage img = FileHandler.getImage("assets/original_img/mario.png");
        if (img == null) {
            System.out.println("ERRO: Não foi possível carregar a imagem");
            System.out.println("Verifique se o arquivo 'assets/original_img/mario.png' existe.");
            return;
        }

        final int width = img.getWidth();
        final int height = img.getHeight();

        System.out.println("Imagem carregada: " + width + "x" + height + " pixels");

        // Criar diretório de saída
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
            System.out.println("Diretório 'output' criado.");
        }

        // Menu principal
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n================================ MENU PRINCIPAL ================================");
            System.out.println("1 - Executar FloodFill com PILHA (Stack)");
            System.out.println("2 - Executar FloodFill com FILA (Queue)");
            // System.out.println("3 - Comparar ambos (Stack vs Queue)");
            System.out.println("3 - Apenas renderizar animação (se já existem imagens)");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    executeFloodFillStack(img, width, height);
                    break;
                case 2:
                    executeFloodFillQueue(img, width, height);
                    break;
                /* case 3:
                    compareBoth(img, width, height);
                    break; */
                case 3:
                    FileHandler.renderImages("output/");
                    break;
                case 0:
                    keepRunning = false;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    private static void executeFloodFillStack(BufferedImage img, int width, int height) {
        System.out.println("\n=== EXECUTANDO COM PILHA (STACK) ===");

        // Criar cópia da imagem
        BufferedImage imageCopy = createImageCopy(img);

        // Configurações
        int x = width / 2;
        int y = height / 2;

        System.out.println("Ponto inicial: (" + x + ", " + y + ")");
        System.out.println("Cor nova: Vermelho");

        // Executar FloodFill
        FloodFillStack floodFill = new FloodFillStack(imageCopy, "output/", 500);

        long startTime = System.currentTimeMillis();
        floodFill.paint(x, y, new Color(255, 0, 0)); // Vermelho
        long endTime = System.currentTimeMillis();

        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");

        // Salvar resultado final
        FileHandler.saveImage(imageCopy, "output/resultado_stack.png");
        System.out.println("Resultado salvo: output/resultado_stack.png");

        // Renderizar animação
        System.out.println("\nIniciando renderização da animação...");
        FileHandler.renderImages("output/");
    }

    private static void executeFloodFillQueue(BufferedImage img, int width, int height) {
        System.out.println("\n=== EXECUTANDO COM FILA (QUEUE) ===");

        // Criar cópia da imagem
        BufferedImage imageCopy = createImageCopy(img);

        // Configurações
        int x = width / 2;
        int y = height / 2;

        System.out.println("Ponto inicial: (" + x + ", " + y + ")");
        System.out.println("Cor nova: Verde");

        // Executar FloodFill
        FloodFillQueue floodFill = new FloodFillQueue(imageCopy, "output/", 500);

        long startTime = System.currentTimeMillis();
        floodFill.paint(x, y, new Color(0, 255, 0)); // Verde
        long endTime = System.currentTimeMillis();

        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");

        // Salvar resultado final
        FileHandler.saveImage(imageCopy, "output/resultado_queue.png");
        System.out.println("Resultado salvo: output/resultado_queue.png");

        // Renderizar animação
        System.out.println("\nIniciando renderização da animação...");
        FileHandler.renderImages("output/");
    }

    private static void compareBoth(BufferedImage img, int width, int height) {
        System.out.println("\n=== COMPARAÇÃO: PILHA vs FILA ===");

        System.out.println("\n--- Executando com PILHA ---");
        executeFloodFillStack(img, width, height);

        // Pausa entre execuções
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- Executando com FILA ---");
        executeFloodFillQueue(img, width, height);

        System.out.println("\n=== COMPARAÇÃO CONCLUÍDA ===");
        System.out.println("Verifique os arquivos:");
        System.out.println("- resultado_stack.png (vermelho)");
        System.out.println("- resultado_queue.png (verde)");
    }

    private static BufferedImage createImageCopy(BufferedImage original) {
        BufferedImage copy = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                original.getType()
        );
        copy.getGraphics().drawImage(original, 0, 0, null);
        return copy;
    }
}