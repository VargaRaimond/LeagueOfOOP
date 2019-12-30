package heroes.strategies;

import heroes.Player;

public class RogueStrategy implements HeroStrategy {

    private static class Const {

        public static final float MINUS_DMG = -0.1f;
        public static final float PLUS_DMG = 0.4f;
    }

    @Override
    public void useStrategy(Player hero) {
        if (hero.getCurrentHp() < hero.getMaxHp() / 7) {
            hero.setCurrentHp(hero.getCurrentHp() + hero.getCurrentHp() / 2);
            hero.updateAbilities(Const.MINUS_DMG);
        } else {
            if(hero.getCurrentHp() < hero.getMaxHp() / 5) {
                hero.setCurrentHp(hero.getCurrentHp() - hero.getCurrentHp() / 7);
                hero.updateAbilities(Const.PLUS_DMG);
            }
        }
    }
}
