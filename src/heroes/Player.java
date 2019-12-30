package heroes;

import common.Constants;
import common.HeroVisitable;
import map.LandType;
import map.Map;
import map.MapCell;

import java.awt.Point;

public abstract class Player implements HeroVisitable {
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

    Player(final Point coordinates, final int baseHp, final int hpScale) {
        this.coordinates = coordinates;
        maxHp = baseHp;
        currentHp = baseHp;
        hpScalePerLevel = hpScale;
        currentLand = Map.getInstance().getCellAt(coordinates);
    }

    public final boolean isAlive() {
        return currentHp > 0;
    }

    public final void levelUp() {
        while (xp >= Constants.MIN_XP + level * Constants.XP_LVL_MULTIP) {
            level++;
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
        currentLand = Map.getInstance().getCellAt(coordinates);
    }

    public abstract void takeDamage(Player other);

    public abstract void dealDamage(Wizard wizard);

    public abstract void dealDamage(Knight knight);

    public abstract void dealDamage(Rogue rogue);

    public abstract void dealDamage(Pyromancer pyromancer);

    public abstract void updateAbilities(final float changer);

}
