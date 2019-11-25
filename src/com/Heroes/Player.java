package com.Heroes;

import java.awt.*;

public abstract class Player {
    public PlayerType type;
    Point coordinates;
    private int maxHp;
    private int currentHp;
    int xp;
    int hpScalePerLevel;
    int level;

    Player(Point coordinates) {
        this.coordinates = coordinates;
        level = 0;
        xp = 0;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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
    }

    public abstract void takeDamage(Player other);

    public abstract void dealDamage(Wizard wizard);

    public abstract void dealDamage(Knight knight);

    public abstract void dealDamage(Rogue rogue);

    public abstract void dealDamage(Pyromancer pyromancer);
}
