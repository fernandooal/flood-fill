package src;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage img = FileHandler.getImage("original_img/mario.png");

        final int width = img.getWidth();
        final int height = img.getHeight();

        // TODO: Implementar Método no FloodFill que recebe a imagem, a coordenada e a cor e faz o preenchimento
        // EXEMPLO:
        // FloodFill.paint(img, x, y, new Color(255, 0, 0, 255)) # várias chamadas;


        // TODO: Implementar o Graphics 2d (fileHandler) que vai gerar o gif
    }
}
