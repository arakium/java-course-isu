import java.awt.*;

public class Blob {
    protected double x, y, r=2;
    protected double dx = 0, dy = 0;
    protected double dr = 0;

    public Blob() {

    }
    public Blob(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Blob(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getR() {
        return r;
    }
    public void setR(double r) {
        this.r = r;
    }

    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    public void setGrowth(double dr) {
        this.dr = dr;
    }

    public void step() {
        x += dx;
        y += dy;
        r += dr;
    }

    public boolean contains(double x2,  double y2) {
        double dx = x-x2;
        double dy = y-y2;
        return dx*dx + dy*dy <= r;
    }

    public void draw(Graphics g) {
        g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
    }
}
