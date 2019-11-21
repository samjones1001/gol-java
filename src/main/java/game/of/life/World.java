package game.of.life;

import java.util.TimerTask;

public class World extends TimerTask {
    private String[][] grid;
    private String[][] previousGrid;
    private Printer printer;
    private Integer[][] neighbourLocations;

    private static final String emptyCell = "   ";
    private static final String populatedCell = " # ";

    public World(Integer size, Printer printer) {
        this.grid = new String[size][size];
        this.printer = printer;
        this.neighbourLocations = new Integer[][] {{0,-1}, {-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}};

        for (String[] row : grid) {
            for (Integer cell = 0; cell < row.length; cell++) {
                row[cell] = emptyCell;
            }
        }
    }

    public String[][] getGrid() {
        return grid;
    }

    public void populate() {
        for (String[] row : grid) {
            for (Integer cell = 0; cell < row.length; cell++) {
                row[cell] = populateCell();
            }
        }
    }

    public void populate(Integer[][] cellsToPopulate) {
        for(Integer[] cellIndex : cellsToPopulate) {
            grid[cellIndex[0]][cellIndex[1]] = populatedCell;
        }
        buildPreviousGrid();
    }

    public void run() {
        printGrid();
        buildPreviousGrid();
        for(Integer row = 0; row < grid.length; row++) {
            for(Integer cell = 0; cell < grid[row].length; cell++) {
                grid[row][cell] = updateCell(row, cell);
            }
        }
    }

    private String populateCell() {
        if (Math.random() < 0.5) {
            return emptyCell;
        }
        return populatedCell;
    }

    private void buildPreviousGrid() {
        previousGrid = grid.clone();
        for (int i = 0; i < previousGrid.length; i++) {
            previousGrid[i] = grid[i].clone();
        }
    }

    private void printGrid() {
        printer.print(buildGridString());
    }

    private String buildGridString() {
        String gridString = "";
        for(String[] row : grid){
            gridString += buildRowString(row) + "\n";
        }

        return gridString;
    }

    private String buildRowString(String[] row) {
        return String.join("", row);
    }

    private String updateCell(Integer rowIndex, Integer cellIndex) {
        if (liveNeighbours(rowIndex, cellIndex) > 3 || liveNeighbours(rowIndex, cellIndex ) < 2) {
            return emptyCell;
        } else if (liveNeighbours(rowIndex, cellIndex) == 3) {
            return populatedCell;
        }
        return previousGrid[rowIndex][cellIndex];
    }

    private Integer liveNeighbours(Integer rowIndex, Integer cellIndex) {
        Integer neighbourCount = 0;

        for (Integer[] indexModifier : neighbourLocations) {
            if (rowAndCellIndexNotNegative(rowIndex, cellIndex, indexModifier) &&
                rowAndCellIndexNotOutOfBounds(rowIndex, cellIndex, indexModifier) &&
                cellIsAlive(rowIndex, cellIndex, indexModifier)) {
                    neighbourCount += 1;
            }
        }
        return neighbourCount;
    }

    private Boolean rowAndCellIndexNotNegative(Integer rowIndex, Integer cellIndex, Integer[] indexModifier) {
        return rowIndex + indexModifier[0] >= 0 && cellIndex + indexModifier[1] >= 0;
    }

    private Boolean rowAndCellIndexNotOutOfBounds(Integer rowIndex, Integer cellIndex, Integer[] indexModifier) {
        return rowIndex + indexModifier[0] < grid.length && cellIndex + indexModifier[1] < grid[rowIndex].length;
    }

    private Boolean cellIsAlive(Integer rowIndex, Integer cellIndex, Integer[] indexModifier) {
        return previousGrid[rowIndex + indexModifier[0]][cellIndex + indexModifier[1]] == populatedCell;
    }
}
