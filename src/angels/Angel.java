package angels;

import common.HeroVisitor;
import common.Observable;
import wizard.GreatWizard;
import common.Observer;

import java.awt.Point;

public abstract class Angel implements HeroVisitor, Observable {
    protected Point position;
    protected AngelType angelType;
    protected Observer greatWizard;

    public abstract boolean isGood();

    public Angel(final AngelType type, final Point coordinates) {
        position = coordinates;
        angelType = type;
    }

    public final AngelType getAngelType() {
        return angelType;
    }

    public final Point getPosition() {
        return position;
    }

    public final void notifyObserver() {
        greatWizard = GreatWizard.getInstance();
        greatWizard.update(this);
    }
}
