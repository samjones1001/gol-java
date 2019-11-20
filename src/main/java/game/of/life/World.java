package game.of.life;

import java.util.Arrays;

public class World {
    private Integer[] grid;

    public World(Integer size) {
        grid = new Integer[size*size];
        Arrays.fill(grid, 0, grid.length - 1, 0);
    }

    public Integer[] getGrid() {
        return grid;
    }

    public void populate(Integer[] cellsToPopulate) {
        for(Integer cell : cellsToPopulate) {
            grid[cell] = 1;
        }
    }
}
