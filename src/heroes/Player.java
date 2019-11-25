package heroes;

import map.LandType;
import map.Map;
import map.MapCell;

import java.awt.*;

public abstract class Player {
    public PlayerType type;
    Point coordinates;
    private int maxHp;
    private int currentHp;
    protected int xp;
    public int hpScalePerLevel;
    protected int level;
    protected LandType landWithBonus;
    protected MapCell currentLand;
    public int currentDotDuration;
    public int currentDotDamage;
    public boolean stunned;

    Player(Point coordinates) {
        this.coordinates = coordinates;
        level = 0;
        xp = 0;
        currentLand = Map.getInstance().getCellAt(coordinates);
        currentDotDuration = 0;
        currentDotDamage = 0;
        stunned = false;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void move(Character direction) {
        if(stunned || getCurrentHp() <= 0) return;
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
}
