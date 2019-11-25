package Heroes;

import java.awt.*;

public class PlayerFactory {
    private static PlayerFactory instance;

    private PlayerFactory() {};

    private static class HeroSpecs {
        public static final int WIZARD_BASE_HP = 400;
        public static final int WIZARD_HP_SCALE = 30;

        public static final int PYRO_BASE_HP = 500;
        public static final int PYRO_HP_SCALE = 50;
    }

    public static PlayerFactory getInstance() {
        if(instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    public Player createHero(final Character type, Point coordinates) {
        switch (type) {
            case('W'): return new Wizard(coordinates, HeroSpecs.WIZARD_BASE_HP, HeroSpecs.WIZARD_HP_SCALE);
            case('K'): return new Knight(coordinates);
            case('P'): return new Pyromancer(coordinates, HeroSpecs.PYRO_BASE_HP, HeroSpecs.PYRO_HP_SCALE);
            case('R'): return new Rogue(coordinates);
            default: return null;
        }
    }

}
