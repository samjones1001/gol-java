package game.of.life;

public class IO implements IOService{
    @Override
    public void output(String message) {
        System.out.println(message);
    }
}
