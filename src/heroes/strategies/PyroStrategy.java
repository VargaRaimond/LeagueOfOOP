package heroes.strategies;

import heroes.Player;

public class PyroStrategy implements HeroStrategy {

    private static class Const {

        public static final float MINUS_DMG = -0.3f;
        public static final float PLUS_DMG = 0.7f;
    }

    @Override
    public void useStrategy(Player hero) {
        if (hero.getCurrentHp() < hero.getMaxHp() / 4) {
            hero.setCurrentHp(hero.getCurrentHp() + hero.getCurrentHp() / 3);
            hero.updateAbilities(Const.MINUS_DMG);
        } else {
            if(hero.getCurrentHp() < hero.getMaxHp() / 3) {
                hero.setCurrentHp(hero.getCurrentHp() - hero.getCurrentHp() / 4);
                hero.updateAbilities(Const.PLUS_DMG);
            }
        }
    }
}
