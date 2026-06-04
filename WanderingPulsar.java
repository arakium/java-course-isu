public class WanderingPulsar extends Wanderer {
    public WanderingPulsar(double x, double y) {
        super(x, y);
        dr = 1 + Math.random(); // random growth
    }

    @Override
    public void step() {
        super.step();
        r += dr;
        if (r < 1 || r > 10) {
            dr = -dr;
            r += dr;
        }
    }
}
