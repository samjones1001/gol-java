package game.of.life;

public class App {
    public static void main(String[] args) {
        Printer printer = new Printer();
        World world = new World(3, printer);
        Integer[][] cellsToPopulate = new Integer[][]{{1,0},{1,1},{1,2}};
        world.populate(cellsToPopulate);

        world.tick();
        world.tick();
    }
}
