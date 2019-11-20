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
}
