package game.of.life;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {
    @Test
    void createsAGridOfSizeNxN() {
        World world = new World(3);

        assertEquals(9, world.getGrid().length);
    }

    @Test
    void cellsDefaultToEmpty() {
        World world = new World(3);

        int cell = new Random().nextInt(world.getGrid().length);
        assertEquals(0, world.getGrid()[cell]);
    }

    @Test
    void populatesCells() {
        World world = new World(3);
        world.populate(new Integer[]{1,4,7});

        assertEquals(1, world.getGrid()[1]);
        assertEquals(1, world.getGrid()[4]);
        assertEquals(1, world.getGrid()[7]);
    }
}
