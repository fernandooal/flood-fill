package src;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileHandler {
    public static BufferedImage getImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Erro carregando imagem!");
        }
        return null;
    }

    public static void saveImage(BufferedImage img, String path){
        try {
            ImageIO.write(img, "png", new File("assets/mario_example.png"));
        } catch (IOException e) {
            System.out.println("Erro salvando imagem!");
        }
    }

    public static Color getPixelColor(BufferedImage img, int x, int y) {
        return new Color(img.getRGB(x, y), true);
    }

    public static void setPixelColor(BufferedImage img, int x, int y, Color color) {
        img.setRGB(x, y, color.getRGB());
    }

    public static void renderImages(String path){
        // TODO: Implementar renderização das imagens com Graphics 2D
    }
}
