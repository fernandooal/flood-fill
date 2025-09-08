package src;

import java.awt.image.BufferedImage;
import src.myutils.Stack;
import java.awt.Color;


public class FloodFill {
    // atributos do FloodFill (ok!)
    private BufferedImage image;
    private Stack<Point> stack;
    private Color originalColor;
    private Color newColor;
    private int height;
    private int width;
    private int pixelCounter;
    private int saveInterval;   // a cada quantos pixels para salvar a imagem
    private String outputPath;
    private int imageNumber;    // para numerar as imagens salvas

    // classe para representar um ponto (ok!)
    public static class Point {
        public int x, y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // construtor (ok!)
    public  FloodFill(BufferedImage image, String outputPath, int saveInterval) {
        this.image = image;
        this.stack = new Stack<>();
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.pixelCounter = 0;
        this.saveInterval = saveInterval; // teste com 1000 pixels (ok!)
        this.outputPath = outputPath;
        this.imageNumber = 0;
    }

    // métodos principais
    public void paint(int x, int y, Color newColor) {
        // verificar coordenadas na imagem (ok!)
        if (!isWithinBounds(x, y)) return;

        // atribuir as cores (ok!)
        this.originalColor = FileHandler.getPixelColor(image, x, y);
        this.newColor = newColor;

        // se a cor original for igual à nova cor, não deve faz nada
        if (originalColor.equals(newColor)) return;

        // resetar os contadores para nova pintura
        this.pixelCounter = 0;
        this.imageNumber = 1;

        stack.push(new Point(x, y));
        executeFloodFill();
    }

    private void executeFloodFill() {
        System.out.println("Iniciando FloodFill");
        while (!stack.isEmpty()) {
            Point point = stack.pop();
            if (!isValidPoint(point.x, point.y)) {
                continue;
            }

            // pintar o pixel atual
            FileHandler.setPixelColor(image, point.x, point.y, newColor);
            pixelCounter++;

            // adicionar vizinhos válidos na pilha
            addNeighbors(point.x, point.y);

            // salvar imagem temporária a cada intervalo definido
            if (pixelCounter % saveInterval == 0) {
                saveTemporaryImage();
                System.out.println("Pixels pintados: " + pixelCounter + " - Imagem salva");
            }
        }
        // salvar imagem final se houver pixels restantes
        if (pixelCounter % saveInterval != 0) {
            saveTemporaryImage();
        }
        System.out.println("FloodFill concluído. Total de pixels pintados: " + pixelCounter);
    }

    private boolean isValidPoint(int x, int y) {
        if (!isWithinBounds(x, y)) return false;

        // verificar se o pixel ainda tem a cor original (ainda não foi pintado)
        Color currentColor = FileHandler.getPixelColor(image, x, y);
        return currentColor.equals(originalColor);
    }

    private void addNeighbors(int x, int y) {
        // vizinhos: (-1,0), (0,1), (1,0), (0,-1)
        // vizinho à esquerda: (-1, 0)
        if (isValidPoint(x - 1, y)) {
            stack.push(new Point(x - 1, y));
        }

        // vizinho acima: (0, -1)
        if (isValidPoint(x, y - 1)) {
            stack.push(new Point(x, y - 1));
        }

        // vizinho à direita: (1, 0)
        if (isValidPoint(x + 1, y)) {
            stack.push(new Point(x + 1, y));
        }

        // vizinho abaixo: (0, 1)
        if (isValidPoint(x, y + 1)) {
            stack.push(new Point(x, y + 1));
        }
    }

    private void saveTemporaryImage() {
        // exemplo: floodfill_001.png, floodfill_002.png, etc.
        try {
            String fileName = String.format("floodfill_%03d.png", imageNumber);
            String fullPath = outputPath + fileName;
            FileHandler.saveImage(image, fullPath);
            imageNumber++;
        } catch (Exception error) {
            System.out.println("Erro ao salvar imagem temporária: " + error.getMessage());
        }
    }

    // métodos auxiliares
    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
