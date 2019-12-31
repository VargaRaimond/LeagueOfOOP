package angels;

import common.Constants;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

import java.awt.Point;

public final class TheDoomer extends Angel {
    public TheDoomer(final AngelType type, final Point coordinates) {
        super(type, coordinates);
    }

    public boolean isGood() {
        return false;
    }

    // kill a hero
    public void visit(final Knight knight) {
        knight.setCurrentHp(Constants.DEAD_PLAYER);
    }
    public void visit(final Pyromancer pyromancer) {
        pyromancer.setCurrentHp(Constants.DEAD_PLAYER);
    }
    public void visit(final Wizard wizard) {
        wizard.setCurrentHp(Constants.DEAD_PLAYER);
    }
    public void visit(final Rogue rogue) {
        rogue.setCurrentHp(Constants.DEAD_PLAYER);
    }
}
