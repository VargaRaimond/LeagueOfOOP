package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class LevelUpAngel extends Angel {
    public LevelUpAngel(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    private static class Modifiers {
        public static final float KNIGHT = 0.1f;
        public static final float PYRO = 0.2f;
        public static final float ROGUE = 0.15f;
        public static final float WIZARD = 0.25f;
    }

    public boolean isGood() {
        return true;
    }

    // give hero xp to level up
    public void visit(final Knight knight) {
        knight.setXp(Constants.MIN_XP + knight.getLevel() * Constants.XP_LVL_MULTIP);
        knight.updateAbilities(Modifiers.KNIGHT);
    }
    public void visit(final Pyromancer pyromancer) {
        pyromancer.setXp(Constants.MIN_XP + pyromancer.getLevel() * Constants.XP_LVL_MULTIP);
        pyromancer.updateAbilities(Modifiers.PYRO);
    }
    public void visit(final Wizard wizard) {
        wizard.setXp(Constants.MIN_XP + wizard.getLevel() * Constants.XP_LVL_MULTIP);
        wizard.updateAbilities(Modifiers.WIZARD);
    }
    public void visit(final Rogue rogue) {
        rogue.setXp(Constants.MIN_XP + rogue.getLevel() * Constants.XP_LVL_MULTIP);
        rogue.updateAbilities(Modifiers.ROGUE);
    }
}
