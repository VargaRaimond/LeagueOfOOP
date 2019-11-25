package main;

public class Main {
    public static void main(String[] args) {
        GameInputLoader gameInputLoader = new GameInputLoader(args[0]);
        GameInput gameInput = gameInputLoader.load();
        GameEngine engine = new GameEngine();
        engine.computeRounds(gameInput, args[1]);
    }
}
