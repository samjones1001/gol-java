package game.of.life;

import java.util.Arrays;

public class World {
    private Integer rowSize;
    private String[] grid;
    private Printer printer;
    private Integer[] neighbourLocations;

    public World(Integer size, Printer printer) {
        this.rowSize = size;
        this.grid = new String[size*size];
        this.printer = printer;
        this.neighbourLocations = new Integer[] {-1, -rowSize-1, -rowSize, -rowSize+1, +1, rowSize+1, rowSize, rowSize-1};

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

    public Integer liveNeighbours(Integer cellIndex) {
        Integer neighbourCount = 0;
        for (Integer indexModifier : neighbourLocations) {
            if (cellIndex + indexModifier >= 0 && cellIndex + indexModifier < grid.length && grid[cellIndex + indexModifier] == "#") {
                neighbourCount += 1;
            }
        }

        return neighbourCount;
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
