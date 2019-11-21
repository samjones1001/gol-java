package game.of.life;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTest {
    @Test
    void createsAGridOfSizeNxN() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);

        assertEquals(9, world.getGrid().length);
    }

    @Test
    void cellsDefaultToEmpty() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);

        int cell = new Random().nextInt(world.getGrid().length);
        assertEquals(" ", world.getGrid()[cell]);
    }

    @Test
    void populatesCells() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{1,4,7});

        assertEquals("#", world.getGrid()[1]);
        assertEquals("#", world.getGrid()[4]);
        assertEquals("#", world.getGrid()[7]);
    }

    @Test
    void printsTheGrid() {
        String expectedOutput = " # \n # \n # \n";
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);

        world.populate(new Integer[]{1,4,7});
        world.tick();

        assertEquals(expectedOutput, io.lastOutput);
    }

    @Test
    void recognisesWhenACellHasNoLiveNeighbours() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{4});

        assertEquals(0, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheLeftOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{3, 4});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheRightOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{4, 5});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheTopOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{1, 4});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheBottomOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{4, 7});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheTopLeftOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{0, 4});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheTopRightOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{4, 2});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheBottomLeftOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{4, 6});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesALiveNeighbourToTheBottomRightOfACell() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{4, 8});

        assertEquals(1, world.liveNeighbours(4));
    }

    @Test
    void recognisesWhenACellHasMultipleLiveNeighbours() {
        MockIO io = new MockIO();
        Printer printer = new Printer(io);
        World world = new World(3, printer);
        world.populate(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8});

        assertEquals(8, world.liveNeighbours(4));
    }
}
