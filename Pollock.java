import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;


public class Pollock extends DrawingGUI {
    private static final int width = 800, height = 600;
    private static final int numBlobs = 20000;
    private static final int numToMove = 10000;

    private BufferedImage result;
    private ArrayList<Blob> blobs;
    public Pollock() {
        super("Pollock", width, height);

        result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a bunch of random blobs.
        generateBlobsList();

        startTimer();
    }
    /**
    * Fills the resultant buffer image with blobs
    * @author Abdulrahman Abdulkader
     */
    public void generateBlobsList() {
        blobs = new ArrayList<Blob>();
        for (int i=0; i<numBlobs; i++) {
            int x = (int)(width*Math.random());
            int y = (int)(height*Math.random());
            Color color = new Color((int) (Math.random() * 16777216));
            blobs.add(new WanderingPixel(x, y, 2, color));
        }
    }

    /**
     * Clears the current canvas of all paintings by creating a new buffered image
     * @author Abdulrahman Abdulkader
     */
    public void clearCanvas() {
        stopTimer();
        blobs.clear();
        result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        repaint();
    }

    /**
     * DrawingGUI method
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(result, 0, 0, null);
        for (Blob blob : blobs) {
            blob.draw(g);
        }
    }


    @Override
    public void handleTimer() {       for (int b = 0; b < numToMove; b++) {
            // Pick a random blob, leave a trail where it is, and ask it to move.
            Blob blob = blobs.get((int)(Math.random()*blobs.size()));
            int x = (int)blob.getX(), y = (int)blob.getY();
            if (x>=0 && x<width && y>=0 && y<height) {
                Color color = ((WanderingPixel)blob).getColor();
                result.setRGB(x, y, color.getRGB());
            }
            blob.step();
        }
        repaint();
    }

    @Override
    public void handleKeyPress(char k) {
        switch (k) {
            case 'c': // clears the canvas
                if (timer.isRunning()) {
                    clearCanvas();
                    System.out.println("The canvas was painted white.");
                }
                else {
                    System.out.println("[ERROR] The canvas is already cleared.");
                }
                break;
            case 'p': // Repopulate the canvas
                if (!timer.isRunning()) {
                    clearCanvas();
                    generateBlobsList();
                    startTimer();
                    System.out.println("The canvas is becoming colorful!");
                }
                else {
                    System.out.println("[ERROR] The canvas is already colorful!");
                }
                break;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Pollock();
            }
        });
    }
}
