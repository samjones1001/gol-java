package game.of.life;

import java.util.Arrays;

public class World {
    private Integer rowSize;
    private String[] grid;
    private Printer printer;

    public World(Integer size, Printer printer) {
        this.rowSize = size;
        this.grid = new String[size*size];
        this.printer = printer;

        Arrays.fill(grid, 0, grid.length, " ");
    }

    public String[] getGrid() {
        return grid;
    }

    public void populate(Integer[] cellsToPopulate) {
        for(Integer cell : cellsToPopulate) {
            grid[cell] = "#";
        }
    }

    public void tick() {
        printGrid();
    }

    private void printGrid() {
        printer.print(buildGridString());
    }

    private String buildGridString() {
        String gridString = "";
        for(int i=0; i<grid.length; i+=rowSize){
            gridString += buildRowString(Arrays.copyOfRange(grid, i, Math.min(grid.length,i+rowSize))) + "\n";
        }

        return gridString;
    }

    private String buildRowString(String[] row) {
        return String.join("", row);
    }
}
