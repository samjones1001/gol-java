package game.of.life;

public class MockIO implements IOService {
    public String lastOutput;

    @Override
    public void output(String message) {
        lastOutput = message;
    }
}
