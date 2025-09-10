package src;

import src.floodfill.FloodFill;
import src.floodfill.FloodFillStack;
import src.floodfill.FloodFillQueue;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("===============================================");
        System.out.println("        ALGORITMO FLOOD FILL");
        System.out.println("===============================================");

        // Carregar imagem
        BufferedImage img;
        try {
            img = setup("assets/original_img/mario.png");
        } catch (IOException e) {
            System.out.println("ERRO: Não foi possível carregar a imagem");
            System.out.println("Verifique se o arquivo 'assets/original_img/mario.png' existe.");
            throw e;
        }

        menu(img);
    }

    private static BufferedImage setup(String path) throws IOException {
        BufferedImage img = FileHandler.getImage(path);
        if (img == null) {
            throw new IOException();
        }

        System.out.println("Imagem carregada: " + img.getWidth() + "x" + img.getHeight() + " pixels");

        // Criar diretório de saída
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
            System.out.println("Diretório 'output' criado.");
        }
        return img;
    }

    private static void menu(BufferedImage img) {
        Scanner scanner = new Scanner(System.in);

        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\n================================ MENU PRINCIPAL ================================");
            System.out.println("1 - Executar FloodFill com PILHA (Stack)");
            System.out.println("2 - Executar FloodFill com FILA (Queue)");
            System.out.println("3 - Apenas renderizar animação (se já existem imagens)");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                case 2:
                    floodFill(img, opcao);
                    break;
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

    private static void floodFill(BufferedImage img, int opcao) {
        System.out.println("\n=== EXECUTANDO MARIO COLOR MAP ===");

        // Criar instância do FloodFill
        FloodFill floodFill;
        if (opcao == 1) {
            floodFill = new FloodFillStack(img, "output/", 500);
        } else {
            floodFill = new FloodFillQueue(img, "output/", 500);
        }

        long startTime = System.currentTimeMillis();
        // Percorrer todas as áreas do Mario
        for (MarioColorMap.ColorArea area : MarioColorMap.getMarioAreas()) {
            System.out.println("\nColorindo: " + area.description);
            System.out.println("Ponto inicial: (" + area.x + ", " + area.y + ")");
            System.out.println("Cor original esperada: " + area.originalColor);
            System.out.println("Nova cor: " + area.newColor);

            floodFill.paint(area.x, area.y, area.newColor);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("\nTempo total de execução: " + (endTime - startTime) + "ms");

        // Salvar resultado final
        FileHandler.saveImage(img, "output/resultado_mario.png");
        System.out.println("Resultado salvo: output/resultado_mario.png");

        // Renderizar animação
        System.out.println("\nIniciando renderização da animação...");
        FileHandler.renderImages("output/");
    }
}