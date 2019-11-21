package game.of.life;

import java.util.Timer;

public class App {
    public static void main(String[] args) {
        World world = new World(25, new Printer());
        world.populate();

        Timer timer = new Timer();
        timer.schedule(world, 0, 1000);
    }
}
