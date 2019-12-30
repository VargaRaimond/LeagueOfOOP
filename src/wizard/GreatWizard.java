package wizard;

import angels.Angel;
import angels.AngelType;
import heroes.Player;

import java.io.BufferedWriter;

public final class GreatWizard implements Observer {

    private static GreatWizard instance;
    BufferedWriter buffer;

    private GreatWizard(BufferedWriter buffer) {
        this.buffer = buffer;
    }

    public static GreatWizard getInstance(BufferedWriter buffer) {
        if (instance == null) {
            instance = new GreatWizard(buffer);
        }
        return instance;
    }

    public static GreatWizard getInstance() {
        return instance;
    }

    @Override
    public void update(Player hero) {
        try {
            System.out.println(hero.getType() + " " + hero.getId() + " reached level " + hero.getLevel());
            buffer.write(hero.getType() + " " + hero.getId() + " reached level " + hero.getLevel());
            buffer.newLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(Angel angel) {
        try {
            System.out.println("Angel " + angel.getAngelType() + " was spawned at "
                    + angel.getPosition().x + " " + angel.getPosition().y);
            buffer.write("Angel " + angel.getAngelType() + " was spawned at "
                    + angel.getPosition().x + " " + angel.getPosition().y);
            buffer.newLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(Player hero1, Player hero2) {
        try {
            System.out.println("Player " + hero1.getType() + " "
                    + hero1.getId() + " was killed by " + hero2.getType() + " " + hero2.getId());
            buffer.write("Player " + hero1.getType() + " "
                    + hero1.getId() + " was killed by " + hero2.getType() + " " + hero2.getId());
            buffer.newLine();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(Angel angel, Player hero) {
        try {
            if (angel.isGood()) {
                System.out.println(angel.getAngelType() + " helped " + hero.getType() + " " + hero.getId());
                buffer.write(angel.getAngelType() + " helped " + hero.getType() + " " + hero.getId());
                buffer.newLine();
            } else {
                System.out.println(angel.getAngelType() + " hit " + hero.getType() + " " + hero.getId());
                buffer.write(angel.getAngelType() + " hit " + hero.getType() + " " + hero.getId());
                buffer.newLine();
            }

            if (angel.getAngelType() == AngelType.Spawner) {
                System.out.println("Player " + hero.getType() + " " + hero.getId() + " was brought to life by an angel");
                buffer.write("Player " + hero.getType() + " " + hero.getId() + " was brought to life by an angel");
                buffer.newLine();
            }

            if (angel.getAngelType() == AngelType.TheDoomer) {
                System.out.println("Player " + hero.getType() + " " + hero.getId() + " was killed by an angel");
                buffer.write("Player " + hero.getType() + " " + hero.getId() + " was killed by an angel");
                buffer.newLine();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
