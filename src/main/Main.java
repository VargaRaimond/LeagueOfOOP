package main;

public final class Main {
    // for checkstyle
    private Main() { }

    public static void main(final String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0]);
        GameInput gameInput = gameInputLoader.load();
        GameEngine engine = new GameEngine(args[1]);
        engine.computeRounds(gameInput);
    }
}
