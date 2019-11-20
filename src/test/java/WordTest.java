import game.of.life.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordTest {
    @Test
    void createsAGridOfSizeNxN() {
        World world = new World(3);

        assertEquals(3, world.getGrid().length);
        assertEquals(3, world.getGrid()[0].length);
    }
}
