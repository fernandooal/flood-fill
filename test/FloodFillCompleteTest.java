package src;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

public class FloodFillCompleteTest {
    public static void main(String[] args) {
        System.out.println("=== TESTE COMPLETO DO FLOODFILL ===\n");

        // carregar imagem
        BufferedImage image = FileHandler.getImage("assets/original_img/mario.png");
        if (image == null) {
            System.out.println("Erro ao carregar imagem");
            return;
        }

        // criar diretório de saída se não existir
        File outputDir = new File("assets/output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
            System.out.println("Diretório de saída criado: assets/output/");
        }

        // configurar FloodFill
        FloodFill floodFill = new FloodFill(image, "assets/output/", 1000);

        // escolher um ponto para testar
        // neste exemplo usei o centro da imagem
        int x = image.getWidth() / 2;
        int y = image.getHeight() / 2;
        Color originalColor = FileHandler.getPixelColor(image, x, y);

        System.out.println("Configurações do teste:");
        System.out.println("- Imagem: " + image.getWidth() + "x" + image.getHeight() + " pixels");
        System.out.println("- Ponto inicial: (" + x + ", " + y + ")");
        System.out.println("- Cor original: RGB(" + originalColor.getRed() + ", " +
                originalColor.getGreen() + ", " + originalColor.getBlue() + ")");
        System.out.println("- Intervalo de salvamento: 50 pixels");
        System.out.println("- Cor nova: Vermelho (255, 0, 0)");

        // executar FloodFill
        System.out.println("\nIniciando FloodFill\n");

        // pintar de vermelho
        long startTime = System.currentTimeMillis();
        floodFill.paint(x, y, new Color(255, 0, 0));
        long endTime = System.currentTimeMillis();

        System.out.println("\nFloodFill concluído");
        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");

        // salvar imagem final
        FileHandler.saveImage(image, "assets/output/mario_final.png");
        System.out.println("Imagem final salva como: mario_final.png");

        // verificar arquivos gerados
        File[] files = outputDir.listFiles((dir, name) -> name.endsWith(".png"));
        if (files != null) {
            System.out.println("\nArquivos gerados (" + files.length + "):");
            for (File file : files) {
                System.out.println("   " + file.getName());
            }
        }

        System.out.println("\n=== TESTE CONCLUÍDO ===");
        System.out.println("Verifique a pasta assets/output/ para ver as imagens geradas");
    }
}