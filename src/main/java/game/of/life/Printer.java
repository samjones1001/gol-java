package game.of.life;

public class Printer {
    private IOService output;

    public Printer() {
        this.output = new IO();
    }

    public Printer(IOService io) {
        this.output = io;
    }

    public void print(String message) {
        output.output(message);
    }
}
