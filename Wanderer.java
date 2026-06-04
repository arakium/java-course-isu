public class Wanderer extends Blob {
    public Wanderer(double x, double y) {
        super(x, y);
    }
    public Wanderer(double x, double y, double r) {
        super(x, y, r);
    }

    @Override
    public void step() {
        dx = 2 * (Math.random() - 0.5);
        dy = 2 * (Math.random() - 0.5);
        x += dx;
        y += dy;
    }
}
