public class Bouncer extends Blob {
    private int xmax, ymax;

    public Bouncer(double x, double y, int xmax, int ymax) {
        super(x, y);
        this.xmax = xmax;
        this.ymax = ymax;

        dx = 2 *  (Math.random() - 0.5);
        dy = 2 * (Math.random() - 0.5);
    }

    public void step() {
        x += dx;
        y += dy;

        if (x > xmax - r) {
            x = xmax - r;
            dx = -dx;
        }
        else if (x < r) {
            x = r;
            dx = -dx;
        }
        if (y > ymax - r) {
            y = ymax - r;
            dy = -dy;
        }
        else if (y < r) {
            y = r;
            dy = -dy;
        }


    }
}
