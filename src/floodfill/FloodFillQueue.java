package src.floodfill;

import src.FileHandler;
import src.myutils.Queue;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FloodFillQueue extends FloodFill {
    private final Queue<Point> queue;

    public FloodFillQueue(BufferedImage image, String outputPath, int saveInterval) {
        super(image, outputPath, saveInterval);
        queue = new Queue<>();
    }

    @Override
    public void paint(int x, int y, Color newColor) {
        super.paint(x, y, newColor);

        queue.enqueue(new Point(x, y));
        executeFloodFill();
    }

    public void executeFloodFill() {
        System.out.println("Iniciando FloodFill - Fila");
        while (!queue.isEmpty()) {
            Point point = queue.dequeue();
            super.processPoint(point);
        }
        super.finishFloodFill();
    }

    protected void addNeighbors(int x, int y) {
        // vizinho à esquerda: (-1, 0)
        if (isValidPoint(x - 1, y)) {
            queue.enqueue(new Point(x - 1, y));
        }

        // vizinho acima: (0, -1)
        if (isValidPoint(x, y - 1)) {
            queue.enqueue(new Point(x, y - 1));
        }

        // vizinho à direita: (1, 0)
        if (isValidPoint(x + 1, y)) {
            queue.enqueue(new Point(x + 1, y));
        }

        // vizinho abaixo: (0, 1)
        if (isValidPoint(x, y + 1)) {
            queue.enqueue(new Point(x, y + 1));
        }
    }
}

