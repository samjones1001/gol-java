package game.of.life;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {
    @Test
    void sendsAMessageToOutput() {
        MockIO mockIO = new MockIO();
        Printer printer = new Printer(mockIO);

        printer.print("Some Message");

        assertEquals("Some Message", mockIO.lastOutput);
    }
}
