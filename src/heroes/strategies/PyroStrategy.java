package heroes.strategies;

import heroes.Player;

public final class PyroStrategy implements HeroStrategy {

    private static class Const {

        public static final float MINUS_DMG = -0.3f;
        public static final float PLUS_DMG = 0.7f;
        public static final int HEAL_STRAT_LIMIT = 4;
        public static final int BUFF_STRAT_LIMIT = 3;
        public static final int HEAL_STRAT = 3;
        public static final int BUFF_STRAT = 4;
    }

    @Override
    public void useStrategy(final Player hero) {
        // decide if we apply a strategy and compute new stats
        if (hero.getCurrentHp() < hero.getMaxHp() / Const.HEAL_STRAT_LIMIT) {
            hero.setCurrentHp(hero.getCurrentHp() + hero.getCurrentHp() / Const.HEAL_STRAT);
            hero.updateAbilities(Const.MINUS_DMG);
        } else {
            if (hero.getCurrentHp() < hero.getMaxHp() / Const.BUFF_STRAT_LIMIT
            && hero.getCurrentHp() > hero.getMaxHp() / Const.HEAL_STRAT_LIMIT) {
                hero.setCurrentHp(hero.getCurrentHp() - hero.getCurrentHp() / Const.BUFF_STRAT);
                hero.updateAbilities(Const.PLUS_DMG);
            }
        }
    }
}
