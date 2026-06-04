import java.awt.*;

public class WanderingPixel extends Wanderer {
    private Color color;
    WanderingPixel(double x,double y,double r, Color c) {
        super(x, y, r);
        this.color = c;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)(x-r), (int)(y-r), (int)(r*2), (int)(r*2));
    }
    public Color getColor() {
        return color;
    }
}
