package heroes.strategies;

import heroes.Player;

public class KnightStrategy implements HeroStrategy {

    private static class Const {

        public static final float MINUS_DMG = -0.2f;
        public static final float PLUS_DMG = 0.5f;
    }

    @Override
    public void useStrategy(Player hero) {
        if (hero.getCurrentHp() < hero.getMaxHp() / 3) {
            hero.setCurrentHp(hero.getCurrentHp() + hero.getCurrentHp() / 4);
            hero.updateAbilities(Const.MINUS_DMG);
        } else {
            if(hero.getCurrentHp() < hero.getMaxHp() / 2) {
                hero.setCurrentHp(hero.getCurrentHp() - hero.getCurrentHp() / 5);
                hero.updateAbilities(Const.PLUS_DMG);
            }
        }
    }
}
