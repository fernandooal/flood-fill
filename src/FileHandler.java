package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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
            ImageIO.write(img, "png", new File(path));
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

    public static void renderImages(String path) {
        System.out.println("=== INICIANDO RENDERIZAÇÃO DA ANIMAÇÃO ===");

        try {
            // Encontrar todas as imagens geradas pelo FloodFill
            File directory = new File(path);
            if (!directory.exists()) {
                System.out.println("Erro: Diretório não encontrado: " + path);
                return;
            }

            // Filtrar apenas arquivos PNG do FloodFill
            File[] imageFiles = directory.listFiles((dir, name) ->
                    name.startsWith("floodfill_") && name.endsWith(".png"));

            if (imageFiles == null || imageFiles.length == 0) {
                System.out.println("Nenhuma imagem encontrada para renderizar");
                return;
            }

            // Ordenar arquivos por nome
            Arrays.sort(imageFiles, Comparator.comparing(File::getName));

            System.out.println("Encontradas " + imageFiles.length + " imagens para animação");

            // Criar e exibir janela de animação
            createAnimationWindow(imageFiles);

        } catch (Exception e) {
            System.out.println("Erro durante renderização: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createAnimationWindow(File[] imageFiles) {
        System.out.println("Iniciando janela de animação...");

        SwingUtilities.invokeLater(() -> {
            try {
                // Carregar primeira imagem para obter dimensões
                BufferedImage firstImage = ImageIO.read(imageFiles[0]);

                // Criar janela principal
                JFrame frame = new JFrame("Animação FloodFill - Trabalho RPEC");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                // Criar painel customizado para exibir imagens
                AnimationPanel imagePanel = new AnimationPanel(firstImage);

                // Painel de controles
                JPanel controlPanel = new JPanel(new FlowLayout());
                controlPanel.setBackground(Color.LIGHT_GRAY);

                // Label de progresso
                JLabel progressLabel = new JLabel("Frame 1 / " + imageFiles.length);
                progressLabel.setFont(new Font("Arial", Font.BOLD, 14));

                // Botões de controle
                JButton playButton = new JButton("▶ Reproduzir");
                JButton pauseButton = new JButton("⏸ Pausar");
                JButton resetButton = new JButton("⏮ Reiniciar");

                pauseButton.setEnabled(false);

                // Controles de velocidade
                JLabel speedLabel = new JLabel("Velocidade:");
                JSlider speedSlider = new JSlider(1, 10, 3);
                speedSlider.setMajorTickSpacing(3);
                speedSlider.setPaintTicks(true);
                speedSlider.setPaintLabels(true);

                controlPanel.add(playButton);
                controlPanel.add(pauseButton);
                controlPanel.add(resetButton);
                controlPanel.add(new JSeparator(SwingConstants.VERTICAL));
                controlPanel.add(speedLabel);
                controlPanel.add(speedSlider);
                controlPanel.add(new JSeparator(SwingConstants.VERTICAL));
                controlPanel.add(progressLabel);

                // Timer para animação
                Timer animationTimer = new Timer(300, null);
                AnimationController controller = new AnimationController(
                        imageFiles, imagePanel, progressLabel, animationTimer, playButton, pauseButton
                );

                // Configurar ações dos botões
                playButton.addActionListener(e -> controller.play());
                pauseButton.addActionListener(e -> controller.pause());
                resetButton.addActionListener(e -> controller.reset());

                // Controle de velocidade
                speedSlider.addChangeListener(e -> {
                    int speed = speedSlider.getValue();
                    int delay = 500 - (speed * 40);
                    animationTimer.setDelay(Math.max(50, delay));
                });

                // Configurar timer
                animationTimer.addActionListener(controller);

                // Montar janela
                frame.add(imagePanel, BorderLayout.CENTER);
                frame.add(controlPanel, BorderLayout.SOUTH);

                // Configurações finais da janela
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                System.out.println("Janela de animação criada com sucesso!");
                System.out.println("Use os controles para reproduzir a animação do FloodFill");

            } catch (IOException e) {
                System.out.println("Erro ao carregar imagens: " + e.getMessage());
            }
        });
    }

    // Classe interna para o painel de animação
    private static class AnimationPanel extends JPanel {
        private BufferedImage currentImage;
        private int frameNumber = 1;
        private int totalFrames = 1;

        public AnimationPanel(BufferedImage firstImage) {
            this.currentImage = firstImage;
            setPreferredSize(new Dimension(firstImage.getWidth() + 100, firstImage.getHeight() + 100));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Usar Graphics2D para renderização de alta qualidade
            Graphics2D g2d = (Graphics2D) g.create();

            // Configurar renderização de alta qualidade
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            if (currentImage != null) {
                // Centralizar imagem no painel
                int imgX = (getWidth() - currentImage.getWidth()) / 2;
                int imgY = (getHeight() - currentImage.getHeight()) / 2;

                // Desenhar imagem com Graphics2D
                g2d.drawImage(currentImage, imgX, imgY, null);

                // Adicionar informações de progresso usando Graphics2D
                drawProgressOverlay(g2d, imgX, imgY, currentImage.getWidth(), currentImage.getHeight());
            }

            g2d.dispose();
        }

        private void drawProgressOverlay(Graphics2D g2d, int imgX, int imgY, int imgWidth, int imgHeight) {
            // Configurar transparência
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

            // Posição da barra de progresso
            int barWidth = imgWidth - 40;
            int barHeight = 20;
            int barX = imgX + 20;
            int barY = imgY + imgHeight + 10;

            // Desenhar fundo da barra
            g2d.setColor(new Color(50, 50, 50, 150));
            g2d.fillRoundRect(barX, barY, barWidth, barHeight, 10, 10);

            // Calcular e desenhar progresso
            double progress = (double) frameNumber / totalFrames;
            int progressWidth = (int) (barWidth * progress);

            // Gradiente para a barra de progresso
            GradientPaint gradient = new GradientPaint(
                    barX, barY, new Color(0, 150, 255),
                    barX + progressWidth, barY, new Color(0, 255, 150)
            );
            g2d.setPaint(gradient);
            g2d.fillRoundRect(barX, barY, progressWidth, barHeight, 10, 10);

            // Borda da barra
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(2f));
            g2d.drawRoundRect(barX, barY, barWidth, barHeight, 10, 10);

            // Texto de progresso com sombra
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2d.setFont(new Font("Arial", Font.BOLD, 14));

            String progressText = String.format("Frame %d / %d (%.1f%%)",
                    frameNumber, totalFrames, progress * 100);

            FontMetrics fm = g2d.getFontMetrics();
            int textX = imgX + (imgWidth - fm.stringWidth(progressText)) / 2;
            int textY = barY + barHeight + 25;

            // Sombra do texto
            g2d.setColor(Color.BLACK);
            g2d.drawString(progressText, textX + 1, textY + 1);

            // Texto principal
            g2d.setColor(Color.WHITE);
            g2d.drawString(progressText, textX, textY);
        }

        public void setCurrentImage(BufferedImage image, int frame, int total) {
            this.currentImage = image;
            this.frameNumber = frame;
            this.totalFrames = total;
            repaint();
        }
    }

    // Classe para controlar a animação
    private static class AnimationController implements ActionListener {
        private final File[] imageFiles;
        private final AnimationPanel imagePanel;
        private final JLabel progressLabel;
        private final Timer timer;
        private final JButton playButton;
        private final JButton pauseButton;
        private int currentFrame = 0;

        public AnimationController(File[] imageFiles, AnimationPanel imagePanel, JLabel progressLabel,
                                   Timer timer, JButton playButton, JButton pauseButton) {
            this.imageFiles = imageFiles;
            this.imagePanel = imagePanel;
            this.progressLabel = progressLabel;
            this.timer = timer;
            this.playButton = playButton;
            this.pauseButton = pauseButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Carregar e exibir próxima imagem
                BufferedImage image = ImageIO.read(imageFiles[currentFrame]);
                imagePanel.setCurrentImage(image, currentFrame + 1, imageFiles.length);

                // Atualizar progresso
                progressLabel.setText("Frame " + (currentFrame + 1) + " / " + imageFiles.length);

                currentFrame++;

                // Verificar se chegou ao fim
                if (currentFrame >= imageFiles.length) {
                    timer.stop();
                    playButton.setEnabled(true);
                    pauseButton.setEnabled(false);
                    System.out.println("Animação concluída!");
                }

            } catch (Exception ex) {
                System.out.println("Erro durante animação: " + ex.getMessage());
                timer.stop();
            }
        }

        public void play() {
            if (currentFrame >= imageFiles.length) {
                reset();
            }
            timer.start();
            playButton.setEnabled(false);
            pauseButton.setEnabled(true);
            System.out.println("Reproduzindo animação...");
        }

        public void pause() {
            timer.stop();
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            System.out.println("Animação pausada");
        }

        public void reset() {
            timer.stop();
            currentFrame = 0;
            try {
                BufferedImage firstImage = ImageIO.read(imageFiles[0]);
                imagePanel.setCurrentImage(firstImage, 1, imageFiles.length);
                progressLabel.setText("Frame 1 / " + imageFiles.length);
            } catch (Exception ex) {
                System.out.println("Erro ao reiniciar: " + ex.getMessage());
            }
            playButton.setEnabled(true);
            pauseButton.setEnabled(false);
            System.out.println("Animação reiniciada");
        }
    }
}