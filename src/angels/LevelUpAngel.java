package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public class LevelUpAngel extends Angel {
    public LevelUpAngel(AngelType type, Point coordinates) {
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

    public void visit(Knight knight) {
        if(knight.isAlive()) {
            knight.setXp(Constants.MIN_XP + knight.getLevel() * Constants.XP_LVL_MULTIP);
            knight.levelUp();
            knight.updateAbilities(Modifiers.KNIGHT);
        }
    }
    public void visit(Pyromancer pyromancer) {
        if(pyromancer.isAlive()) {
            pyromancer.setXp(Constants.MIN_XP + pyromancer.getLevel() * Constants.XP_LVL_MULTIP);
            pyromancer.levelUp();
            pyromancer.updateAbilities(Modifiers.PYRO);
        }
    }
    public void visit(Wizard wizard) {
        if(wizard.isAlive()) {
            wizard.setXp(Constants.MIN_XP + wizard.getLevel() * Constants.XP_LVL_MULTIP);
            wizard.levelUp();
            wizard.updateAbilities(Modifiers.WIZARD);
        }
    }
    public void visit(Rogue rogue) {
        if(rogue.isAlive()) {
            rogue.setXp(Constants.MIN_XP + rogue.getLevel() * Constants.XP_LVL_MULTIP);
            rogue.levelUp();
            rogue.updateAbilities(Modifiers.ROGUE);
        }
    }
}
