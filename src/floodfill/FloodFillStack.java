package src.floodfill;

import src.FileHandler;
import src.myutils.Stack;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFillStack extends FloodFill{
    private final Stack<Point> stack;

    public FloodFillStack(BufferedImage image, String outputPath, int saveInterval) {
        super(image, outputPath, saveInterval);
        stack = new Stack<>();
    }

    @Override
    public void paint(int x, int y, Color newColor) {
        super.paint(x, y, newColor);

        stack.push(new Point(x, y));
        executeFloodFill();
    }

    public void executeFloodFill() {
        System.out.println("Iniciando FloodFill - Pilha");
        while (!stack.isEmpty()) {
            Point point = stack.pop();
            super.processPoint(point);
        }
        super.finishFloodFill();
    }

    protected void addNeighbors(int x, int y) {
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
}
