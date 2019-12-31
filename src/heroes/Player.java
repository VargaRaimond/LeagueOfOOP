package heroes;

import angels.Angel;
import common.Constants;
import common.HeroVisitable;
import common.Observable;
import heroes.strategies.HeroStrategy;
import map.LandType;
import map.Map;
import map.MapCell;
import wizard.GreatWizard;
import common.Observer;

import java.awt.Point;

public abstract class Player implements HeroVisitable, Observable {
    protected int id;
    protected PlayerType type;
    protected Point coordinates;
    private int maxHp;
    private int currentHp;
    protected int xp;
    protected int hpScalePerLevel;
    protected int level;
    protected LandType landWithBonus;
    protected MapCell currentLand;
    protected int currentDotDuration;
    protected int currentDotDamage;
    protected boolean stunned;
    protected HeroStrategy strategy;
    protected Observer greatWizard;

    Player(final Point coordinates, final int baseHp, final int hpScale, final int id) {
        this.coordinates = coordinates;
        maxHp = baseHp;
        currentHp = baseHp;
        hpScalePerLevel = hpScale;
        currentLand = Map.getInstance().getCellAt(coordinates);
        this.id = id;
    }

    public final boolean isAlive() {
        return currentHp > 0;
    }

    public final void levelUp() {
        while (xp >= Constants.MIN_XP + level * Constants.XP_LVL_MULTIP) {
            level++;
            // notify the greatWizard
            notifyObserver();
            maxHp += hpScalePerLevel;
            currentHp = maxHp;
        }
    }

    public final float getLandBonus() {
        if (currentLand.getType() == landWithBonus) {
            return currentLand.getLandModifier();
        } else {
            return 0f;
        }
    }

    public final int getMaxHp() {
        return maxHp;
    }

    public final int getXp() {
        return xp;
    }

    public final void setXp(final int xp) {
        this.xp = xp;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(final int level) {
        this.level = level;
    }

    public final int getCurrentHp() {
        return currentHp;
    }

    public final int getCurrentDotDuration() {
        return currentDotDuration;
    }

    public final void setCurrentDotDuration(final int currentDotDuration) {
        this.currentDotDuration = currentDotDuration;
    }

    public final int getCurrentDotDamage() {
        return currentDotDamage;
    }

    public final void setCurrentDotDamage(final int currentDotDamage) {
        this.currentDotDamage = currentDotDamage;
    }

    public final void setStunned(final boolean stunned) {
        this.stunned = stunned;
    }

    public final int getId() {
        return id;
    }

    public final PlayerType getType() {
        return type;
    }

    public final void setCurrentHp(final int currentHp) {
        this.currentHp = currentHp;
    }

    public final Point getPosition() {
        return coordinates;
    }

    public final void move(final Character direction) {
        if (stunned || getCurrentHp() <= 0) {
            return;
        }
        switch (direction) {
            case('U') :
                coordinates.x--;
                break;
            case('D') :
                coordinates.x++;
                break;
            case('L') :
                coordinates.y--;
                break;
            case('R') :
                coordinates.y++;
                break;
            default : break;
        }
        // let heroes walk outside map - checker issue
        if (coordinates.x >= 0 && coordinates.y >= 0 && coordinates.x < Map.getInstance().getSize()
                && coordinates.y < Map.getInstance().getSize()) {
            currentLand = Map.getInstance().getCellAt(coordinates);
        }
        // choose a strategy
        strategy.useStrategy(this);
    }

    public abstract void takeDamage(Player other);

    public abstract void dealDamage(Wizard wizard);

    public abstract void dealDamage(Knight knight);

    public abstract void dealDamage(Rogue rogue);

    public abstract void dealDamage(Pyromancer pyromancer);

    public abstract void updateAbilities(Float changer);

    public final void notifyObserver() {
        greatWizard = GreatWizard.getInstance();
        greatWizard.update(this);
    }

    public final void notifyObserver(final Player other) {
        greatWizard = GreatWizard.getInstance();
        greatWizard.update(this, other);
    }

    public final void notifyObserver(final Angel other) {
        greatWizard = GreatWizard.getInstance();
        greatWizard.update(other, this);
    }

}
