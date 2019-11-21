package game.of.life;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {
    @Test
    void createsAGridOfSizeNxN() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);

        assertEquals(3, world.getGrid().length);
        assertEquals(3, world.getGrid()[0].length);

    }

    @Test
    void cellsDefaultToEmpty() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);

        int row = new Random().nextInt(world.getGrid().length);
        int cell = new Random().nextInt(world.getGrid()[row].length);
        assertEquals(" ", world.getGrid()[row][cell]);
    }

    @Test
    void populatesCells() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,1},{1,1},{2,1}});

        assertEquals("#", world.getGrid()[0][1]);
        assertEquals("#", world.getGrid()[1][1]);
        assertEquals("#", world.getGrid()[2][1]);
    }

    @Test public void testPopulatesCellsAtRandom() {
        String[] expectedCellContents = {" ","#"};
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate();

        String[] row = world.getGrid()[new Random().nextInt(world.getGrid().length)];
        String cell = row[new Random().nextInt(row.length)];
        assertTrue(Arrays.asList(expectedCellContents).contains(cell));
    }

    @Test
    void printsTheGrid() {
        String expectedOutput = " # \n # \n # \n";
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);

        world.populate(new Integer[][]{{0,1},{1,1},{2,1}});
        world.tick();

        assertEquals(expectedOutput, io.lastOutput);
    }

    @Test
    void aLiveCellWithMoreThanThreeNeighboursDies() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}});

        world.tick();

        assertEquals(" ", world.getGrid()[1][1]);
    }

    @Test
    void aLiveCellWithLessThanTwoNeighboursDies() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {1,1}});

        world.tick();

        assertEquals(" ", world.getGrid()[1][1]);
    }

    @Test
    void aLiveCellWithTwoNeighboursSurvives() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {0,1}, {1,1}});

        world.tick();

        assertEquals("#", world.getGrid()[1][1]);
    }

    @Test
    void aLiveCellWithThreeNeighboursSurvives() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {0,1}, {0,2}, {1,1}});

        world.tick();

        assertEquals("#", world.getGrid()[1][1]);
    }

    @Test
    void aDeadCellWithMoreThanThreeNeighboursIsStillDead() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {0,1}, {0,2}, {1,0}, {1,1}, {1,2}, {2,0}, {2,1}, {2,2}});

        world.tick();

        assertEquals(" ", world.getGrid()[1][1]);
    }

    @Test
    void aDeadCellWithLessThanTwoNeighboursIsStillDead() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{1,1}});

        world.tick();

        assertEquals(" ", world.getGrid()[1][1]);
    }

    @Test
    void aDeadCellWithTwoNeighboursIsStillDead() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {0,1}});

        world.tick();

        assertEquals(" ", world.getGrid()[1][1]);
    }

    @Test
    void aDeadCellWithThreeNeighboursComesToLife() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{0,0}, {0,1}, {0,2}});
        world.tick();

        assertEquals("#", world.getGrid()[1][1]);
    }

    @Test
    void gridUpdatesCorrectlyAccordingToRules() {
        String[][] expectedOutput = new String[][]{{" ", "#", " "}, {" ", "#", " "}, {" ", "#", " "}};
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[][]{{1,0}, {1,1}, {1,2}});
        world.tick();

        assertArrayEquals(expectedOutput, world.getGrid());
    }


}
