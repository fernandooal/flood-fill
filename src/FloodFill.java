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
    private int width;
    private int height;
    private int pixelCounter;
    private int saveInterval;   // a cada quantos pixels salvar a imagem
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




}
