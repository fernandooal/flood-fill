package src;

import java.awt.Color;

public class MarioColorMap {

    // Classe interna para representar uma área a ser colorida
    public static class ColorArea {
        public int x;
        public int y;
        public Color originalColor;  // RGB atual da área
        public Color newColor;       // RGB desejado
        public String description;

        public ColorArea(int x, int y, Color originalColor, Color newColor, String description) {
            this.x = x;
            this.y = y;
            this.originalColor = originalColor;
            this.newColor = newColor;
            this.description = description;
        }
    }

    // Mapeamento das áreas do Mario baseado nos dados do GIMP
    public static ColorArea[] getMarioAreas() {
        return new ColorArea[] {
                // Rosto
                new ColorArea(480, 410,
                        new Color(255, 255, 255),
                        new Color(255, 228, 181),
                        "Rosto do Mario"),

                // Chapéu
                new ColorArea(364, 156,
                        new Color(56, 56, 56),
                        new Color(255, 0, 0),
                        "Chapéu principal"),

                // Aba do chapéu
                new ColorArea(490, 228,
                        new Color(56, 56, 56),
                        new Color(255, 0, 0),
                        "Aba do chapéu"),

                // Costeletas/barba
                new ColorArea(336, 403,
                        new Color(56, 56, 56),
                        new Color(139, 69, 19),
                        "Costeletas/barba"),

                // Bigode
                new ColorArea(513, 484,
                        new Color(56, 56, 56),
                        new Color(139, 69, 19),
                        "Bigode"),

                // Camisa (lado esquerdo)
                new ColorArea(334, 609,
                        new Color(255, 255, 255),
                        new Color(255, 0, 0),
                        "Camisa esquerda"),

                // Camisa (lado direito)
                new ColorArea(481, 604,
                        new Color(255, 255, 255),
                        new Color(255, 0, 0),
                        "Camisa direita"),

                // Macacão/calça
                new ColorArea(495, 777,
                        new Color(56, 56, 56),
                        new Color(0, 0, 255),
                        "Macacão azul"),

                // Botão do macacão
                new ColorArea(480, 690,
                        new Color(255, 255, 255),
                        new Color(255, 255, 0),
                        "Botão amarelo"),

                // Mão direita
                new ColorArea(288, 711,
                        new Color(255, 255, 255),
                        new Color(255, 228, 181),
                        "Mão direita"),

                // Sapato direito
                new ColorArea(355, 955,
                        new Color(56, 56, 56),
                        new Color(139, 69, 19),
                        "Sapato direito"),

                // Sapato esquerdo
                new ColorArea(547, 930,
                        new Color(56, 56, 56),
                        new Color(139, 69, 19),
                        "Sapato esquerdo")
        };
    }
}