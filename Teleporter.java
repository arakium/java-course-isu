public class Teleporter extends Blob {
    protected int xmax, ymax;

    public Teleporter(double x, double y, int xmax, int ymax) {
        super(x, y);
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public void teleport() {
        x = Math.random() * xmax;
        y = Math.random() * ymax;
    }
    @Override
    public void step() {
        teleport();
    }
}
