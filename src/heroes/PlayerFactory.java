package heroes;

import java.awt.Point;

public final class PlayerFactory {
    private static PlayerFactory instance;

    private static class HeroSpecs {
        public static final int WIZARD_BASE_HP = 400;
        public static final int WIZARD_HP_SCALE = 30;

        public static final int PYRO_BASE_HP = 500;
        public static final int PYRO_HP_SCALE = 50;

        public static final int KNIGHT_BASE_HP = 900;
        public static final int KNIGHT_HP_SCALE = 80;

        public static final int ROGUE_BASE_HP = 600;
        public static final int ROGUE_HP_SCALE = 40;
    }

    public static PlayerFactory getInstance() {
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    public Player createHero(final Character type, final Point position) {
        switch (type) {
            case('W'):
                return new Wizard(position, HeroSpecs.WIZARD_BASE_HP, HeroSpecs.WIZARD_HP_SCALE);
            case('K'):
                return new Knight(position, HeroSpecs.KNIGHT_BASE_HP, HeroSpecs.KNIGHT_HP_SCALE);
            case('P'):
                return new Pyromancer(position, HeroSpecs.PYRO_BASE_HP, HeroSpecs.PYRO_HP_SCALE);
            case('R'):
                return new Rogue(position, HeroSpecs.ROGUE_BASE_HP, HeroSpecs.ROGUE_HP_SCALE);
            default: return null;
        }
    }

}
