import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.*;


public class PaintedImage extends DrawingGUI {
    private static final int numBlobs = 20000;			//
    private static final int numToMove = 5000;			//

    private BufferedImage original;
    private BufferedImage result;
    private ArrayList<Blob> blobs;

    public PaintedImage(BufferedImage original) {
        super("Animated Picture", original.getWidth(), original.getHeight());

        this.original = original;
        result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);

        generateBlobsList();

        startTimer();
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
    public void generateBlobsList() {
        blobs = new ArrayList<Blob>();
        for (int i=0; i<numBlobs; i++) {
            int x = (int)(width*Math.random());
            int y = (int)(height*Math.random());
            Color color = new Color((int) (Math.random() * 16777216));
            blobs.add(new WanderingPixel(x, y, 1, color));
        }
    }
    @Override
    public void handleMousePress(int x, int y) {

        if (timer.isRunning()) { //Clears the current image into a blank image
            stopTimer();
            blobs.clear();
            result = original;
            repaint();
        }
        else { // repopulate blobs on a blank image
            result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
            generateBlobsList();
            startTimer();
        }
    }

    /**
     * DrawingGUI method
     */
    @Override
    public void handleTimer() {
        for (int b = 0; b < numToMove; b++) {
            // Pick a random blob, leave a trail where it is, and ask it to move.
            Blob blob = blobs.get((int)(Math.random()*blobs.size()));
            int x = (int)blob.getX(), y = (int)blob.getY();
            // Careful to stay within the image
            if (x>=0 && x<width && y>=0 && y<height) {
                result.setRGB(x, y, original.getRGB(x, y));
            }
            blob.step();

        }
        repaint();

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaintedImage(loadImage("src/baker.jpg"));
            }
        });
    }
}
