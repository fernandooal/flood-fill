package src.floodfill;

import java.awt.image.BufferedImage;

import src.FileHandler;

import java.awt.Color;

public abstract class FloodFill {
    protected BufferedImage image;
    protected Color originalColor;
    protected Color newColor;
    protected int height;
    protected int width;
    protected int pixelCounter;
    protected int saveInterval;
    protected String outputPath;
    protected int imageNumber;

    public FloodFill(BufferedImage image, String outputPath, int saveInterval) {
        this.image = image;
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.pixelCounter = 0;
        this.saveInterval = saveInterval;
        this.outputPath = outputPath;
        this.imageNumber = 0;
    }

    public void paint(int x, int y, Color newColor) {
        if (!isWithinBounds(x, y)) return;

        // cores
        this.originalColor = FileHandler.getPixelColor(image, x, y);
        this.newColor = newColor;

        if (originalColor.equals(newColor)) return;

        // resetar os contadores para nova pintura
        this.pixelCounter = 0;
    }

    abstract void executeFloodFill();

    abstract void addNeighbors(int x, int y);

    protected void processPoint(Point point) {
        if (!isValidPoint(point.x, point.y)) {
            return;
        }

        // pintar o pixel atual
        FileHandler.setPixelColor(image, point.x, point.y, newColor);
        pixelCounter++;

        // adicionar vizinhos válidos
        addNeighbors(point.x, point.y);

        // salvar imagem temporária
        if (pixelCounter % saveInterval == 0) {
            saveTemporaryImage();
            System.out.println("Pixels pintados: " + pixelCounter + " - Imagem salva");
        }
    }

    protected void finishFloodFill() {
        if (pixelCounter % saveInterval != 0) {
            saveTemporaryImage();
        }
        System.out.println("FloodFill concluído. Total de pixels pintados: " + pixelCounter);
    }

    boolean isValidPoint(int x, int y) {
        if (!isWithinBounds(x, y)) return false;

        // verificar se o pixel ainda tem a cor original (ainda não foi pintado)
        Color currentColor = FileHandler.getPixelColor(image, x, y);
        return currentColor.equals(originalColor);
    }

    void saveTemporaryImage() {
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

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
