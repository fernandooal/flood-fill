package src;

import java.awt.image.BufferedImage;
import src.myutils.Stack;
import java.awt.Color;


public class FloodFill {
    // TODO: Implementar classe Fill
    // metodo paint (implementa o loop + pilha/fila)
    // metodo identificar vizinhos (fazendo validações)
    // metodo para salvar imagens a cada 1k pixel

    // atributos do FloodFill
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

    // classe para representar um ponto
    public static class Point {
        public int x, y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // construtor
    public  FloodFill(BufferedImage image, String outputPath, int saveInterval) {
        this.image = image;
        this.stack = new Stack<>();
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.pixelCounter = 0;
        this.saveInterval = saveInterval; // exemplo: testar com 1000 pixels
        this.outputPath = outputPath;
        this.imageNumber = 0;
    }

    // métodos principais
    public void paint(int x, int y, Color newColor) {
        // verificar coordenadas na imagem
        if (!isWithinBounds(x, y)) return;

        // atribuir as cores
        this.originalColor = FileHandler.getPixelColor(image, x, y);
        this.newColor = newColor;

        // lógica da pintura
        if (originalColor.equals(newColor)) return;
        stack.push(new Point(x, y));
        //executeFloodFill();
    }

    private void executeFloodFill() {
        // TODO: Implementar o algoritmo principal
        // while (!stack.isEmpty()) {
        //     Point point = stack.pop();
        //     // Verificar se já foi pintado, se está dentro dos limites, etc.
        //     // Pintar o pixel
        //     // Adicionar vizinhos válidos na pilha
        //     // Incrementar contador e salvar imagem se necessário
        // }
    }

    private boolean isValidPoint(int x, int y) {
        // TODO: Verificar se o ponto está dentro dos limites e se tem a cor original
        return false; // Exemplo
    }

    private void addNeighbors(int x, int y) {
        // TODO: Adicionar os 4 vizinhos na pilha se forem válidos
        // Vizinhos: (-1,0), (0,1), (1,0), (0,-1)
    }

    private void saveTemporaryImage() {
        // TODO: Salvar imagem atual com nome sequencial
        // Ex: floodfill_001.png, floodfill_002.png, etc.
        imageNumber++;
    }

    // métodos auxiliares
    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
