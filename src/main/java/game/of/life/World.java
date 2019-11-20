package game.of.life;

public class World {
    private Integer[][] grid;

    public World(Integer size) {
        grid = new Integer[size][size];
    }

    public Integer[][] getGrid() {
        return grid;
    }
}
