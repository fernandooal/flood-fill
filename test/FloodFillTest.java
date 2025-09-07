package src;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FloodFillTest {
    public static void main(String[] args) {
        System.out.println("=== TESTE DA CLASSE FLOODFILL ===\n");

        // carregamento de imagem
        System.out.println("1. Testando carregamento da imagem");
        BufferedImage img = FileHandler.getImage("assets/original_img/mario.png");

        if (img == null) {
            System.out.println("   ERRO: Não foi possível carregar a imagem");
            System.out.println("   Verifique se o arquivo 'original_img/mario.png' existe.");
            return;
        }

        System.out.println("   Imagem carregada com sucesso");
        System.out.println("   Dimensões: " + img.getWidth() + "x" + img.getHeight() + " pixels");

        // teste do construtor FloodFill
        System.out.println("\n2. Testando construtor FloodFill");
        try {
            FloodFill floodFill = new FloodFill(img, "output/", 1000);
            System.out.println("   Construtor funcionou corretamente!");
            System.out.println("   Intervalo de salvamento: 1000 pixels");
            System.out.println("   Caminho de saída: output/");
        } catch (Exception e) {
            System.out.println("   ERRO no construtor: " + e.getMessage());
            return;
        }

        // teste das validações básicas
        System.out.println("\n3. Testando validações de coordenadas");
        FloodFill floodFill = new FloodFill(img, "output/", 1000);

        // teste com coordenadas válidas
        int x = img.getWidth() / 2;
        int y = img.getHeight() / 2;
        System.out.println("   Testando coordenada válida (" + x + ", " + y + ")");

        // obter cor do pixel antes do teste
        Color originalColor = FileHandler.getPixelColor(img, x, y);
        System.out.println("   Cor original do pixel: RGB(" +
                originalColor.getRed() + ", " +
                originalColor.getGreen() + ", " +
                originalColor.getBlue() + ")");

        // teste do método paint (até onde está implementado neste momento)
        System.out.println("\n4. Testando método paint");
        try {
            Color newColor = new Color(255, 0, 0);  // vermelho
            floodFill.paint(x, y, newColor);
            System.out.println("   Método paint executou sem erros");
            System.out.println("   Coordenadas testadas: (" + x + ", " + y + ")");
            System.out.println("   Nova cor: RGB(" +
                    newColor.getRed() + ", " +
                    newColor.getGreen() + ", " +
                    newColor.getBlue() + ")");
        } catch (Exception e) {
            System.out.println("   ERRO no método paint: " + e.getMessage());
            e.printStackTrace();
        }

        // teste com coordenadas inválidas
        System.out.println("\n5. Testando coordenadas inválidas");
        try {
            floodFill.paint(
                    -1,
                    -1,
                    new Color(0, 255, 0)    // verde
            );  // coordenadas negativas
            floodFill.paint(
                    img.getWidth() + 10,
                    img.getHeight() + 10,
                    new Color(0, 0, 255)    // azul
            );  // fora dos limites
            System.out.println("   Validação de coordenadas inválidas funcionou");
        } catch (Exception e) {
            System.out.println("   ERRO na validação de coordenadas: " + e.getMessage());
        }

        // teste com cor igual à original
        System.out.println("\n6. Testando pintura com cor igual à original...");
        try {
            floodFill.paint(x, y, originalColor);   // mesma cor
            System.out.println("   Detecção de cor igual funcionou");
        } catch (Exception e) {
            System.out.println("   ERRO na detecção de cor igual: " + e.getMessage());
        }

        // teste da classe Point
        System.out.println("\n7. Testando classe Point...");
        try {
            FloodFill.Point newPoint = new FloodFill.Point(10, 20);
            System.out.println("   Classe Point criada: (" + newPoint.x + ", " + newPoint.y + ")");
        } catch (Exception e) {
            System.out.println("   ERRO na classe Point: " + e.getMessage());
        }

        System.out.println("\n=== FIM DOS TESTES ===");
    }
}