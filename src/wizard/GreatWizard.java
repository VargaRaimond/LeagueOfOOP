package wizard;

import angels.Angel;
import angels.AngelType;
import common.Observer;
import heroes.Player;

import java.io.BufferedWriter;

public final class GreatWizard implements Observer {

    private static GreatWizard instance;
    private BufferedWriter buffer;

    private GreatWizard(final BufferedWriter buffer) {
        this.buffer = buffer;
    }

    public static GreatWizard getInstance(final BufferedWriter buffer) {
        if (instance == null) {
            instance = new GreatWizard(buffer);
        }
        return instance;
    }

    public static GreatWizard getInstance() {
        return instance;
    }

    @Override
    public void update(final Player hero) {
        // update if a hero levels up
        try {
            buffer.write(hero.getType() + " " + hero.getId()
                    + " reached level " + hero.getLevel());
            buffer.newLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(final Angel angel) {
        // update if a new angel appeared on map
        try {
            buffer.write("Angel " + angel.getAngelType() + " was spawned at "
                    + angel.getPosition().x + " " + angel.getPosition().y);
            buffer.newLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(final Player hero1, final Player hero2) {
        // update if a hero was killed in a fight
        try {
            buffer.write("Player " + hero1.getType() + " "
                    + hero1.getId() + " was killed by " + hero2.getType() + " " + hero2.getId());
            buffer.newLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(final Angel angel, final Player hero) {
        // update if an angel interacted with a hero
        try {
            if (angel.isGood()) {
                buffer.write(angel.getAngelType() + " helped "
                        + hero.getType() + " " + hero.getId());
                buffer.newLine();
            } else {
                buffer.write(angel.getAngelType() + " hit " + hero.getType() + " " + hero.getId());
                buffer.newLine();
            }

            // for Spawner, TheDoomer and Dracula check if they killed/revived a player
            if (angel.getAngelType() == AngelType.Spawner) {
                buffer.write("Player " + hero.getType() + " "
                        + hero.getId() + " was brought to life by an angel");
                buffer.newLine();
            }

            if (angel.getAngelType() == AngelType.TheDoomer
                    || (angel.getAngelType() == AngelType.Dracula && !hero.isAlive())) {
                buffer.write("Player " + hero.getType() + " "
                        + hero.getId() + " was killed by an angel");
                buffer.newLine();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
