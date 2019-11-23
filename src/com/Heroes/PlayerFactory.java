package com.Heroes;

public class PlayerFactory {
    private static PlayerFactory instance;

    private PlayerFactory() {};

    public static PlayerFactory getInstance() {
        if(instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    public Player createHero(final Character type, final int x, final int y) {
        switch (type) {
            case('W'): return new Wizard(x, y);
            case('K'): return new Knight(x, y);
            case('P'): return new Pyromancer(x, y);
            case('R'): return new Rogue(x, y);
            default: return null;
        }
    }

}
