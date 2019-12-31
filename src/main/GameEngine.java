package main;

import angels.Angel;
import angels.AngelType;
import common.Constants;
import heroes.Player;
import heroes.PlayerType;
import wizard.GreatWizard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public final class GameEngine {

    private List<Player> allPlayers;
    private List<List<Angel>> allAngels;
    private FileWriter fout;
    private BufferedWriter buffer;

    public GameEngine(final String out) {
        // prepare the buffer
        try {
            fout = new FileWriter(out);
            buffer = new BufferedWriter(fout);
            GreatWizard.getInstance(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void computeRounds(final GameInput input) {
        try {
            allPlayers = input.getPlayers();
            allAngels = input.getAngels();
            for (int roundNr = 0; roundNr < input.getRounds(); roundNr++) {

                buffer.write("~~ Round " + (roundNr + 1) + " ~~");
                buffer.newLine();

                for (Player hero : allPlayers) {
                    if (hero.getCurrentDotDamage() != 0 || hero.getCurrentDotDuration() != 0) {
                        // give damage over time damage and decrease duration
                        hero.setCurrentHp(hero.getCurrentHp() - hero.getCurrentDotDamage());
                        hero.setCurrentDotDuration(hero.getCurrentDotDuration() - 1);
                        if (hero.getCurrentDotDuration() == 0) {
                            hero.setCurrentDotDamage(0);
                        }
                    }
                }

                moveHeroes(input.getMovesAt(roundNr));

                computeFights();

                // add angels for current round and apply their bonuses
                if (!(allAngels.get(roundNr).isEmpty())) {
                    for (Angel angel : allAngels.get(roundNr)) {
                        angel.notifyObserver();
                        for (Player hero : allPlayers) {
                            if (hero.getPosition().equals(angel.getPosition())) {
                                if (angel.getAngelType() != AngelType.Spawner && hero.isAlive()) {
                                    hero.accept(angel);
                                    hero.notifyObserver(angel);
                                    // check if someone leveled up after interactions with angel
                                    hero.levelUp();
                                } else {
                                    // only the Spawner interacts with a dead hero
                                    if (angel.getAngelType() == AngelType.Spawner
                                            && !(hero.isAlive())) {
                                        hero.accept(angel);
                                        hero.notifyObserver(angel);
                                        hero.levelUp();
                                    }
                                }
                            }
                        }
                    }
                }
                buffer.newLine();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        printScoreboard();
    }

    void computeFights() {
        for (int i = 0; i < allPlayers.size() - 1; i++) {
            for (int j = i + 1; j < allPlayers.size(); j++) {
                // check if 2 players are on the same Mapcell
                if (allPlayers.get(i).getPosition().equals(allPlayers.get(j).getPosition())) {
                    if (allPlayers.get(i).isAlive() && allPlayers.get(j).isAlive()) {
                        // Wizard attacks last so I can compute damage for Deflect
                        if (allPlayers.get(i).getType().equals(PlayerType.Wizard)) {
                            allPlayers.get(i).takeDamage(allPlayers.get(j));
                            allPlayers.get(j).takeDamage(allPlayers.get(i));
                        } else {
                            allPlayers.get(j).takeDamage(allPlayers.get(i));
                            allPlayers.get(i).takeDamage(allPlayers.get(j));
                        }
                        // after the fight update xp and level
                        checkExpAndLevelUp(allPlayers.get(i), allPlayers.get(j));
                    }
                }
            }
            if (allPlayers.get(i).getCurrentDotDuration() == 0) {
                allPlayers.get(i).setStunned(false);
            }
        }
    }

    void checkExpAndLevelUp(final Player p1, final Player p2) {
        if (!p2.isAlive()) {
            // only give xp if they didn't kill each other
            if (p1.isAlive()) {
                p1.setXp(p1.getXp() + Math.max(Constants.NO_XP, Constants.BASE_KILL_XP
                        - (p1.getLevel() - p2.getLevel()) * Constants.LVL_DIF_MULTIP));
            }
            // notify observer that p1 killed p2
            p2.notifyObserver(p1);
        }

        if (!p1.isAlive()) {
            if (p2.isAlive()) {
                p2.setXp(p2.getXp() + Math.max(Constants.NO_XP, Constants.BASE_KILL_XP
                        - (p2.getLevel() - p1.getLevel()) * Constants.LVL_DIF_MULTIP));
            }
            p1.notifyObserver(p2);
        }

        // only level up if they didn't kill each other
        if (p1.isAlive()) {
            p1.levelUp();
        }

        if (p2.isAlive()) {
            p2.levelUp();
        }

    }

    void moveHeroes(final String moves) {
        for (int i = 0; i < allPlayers.size(); i++) {
            allPlayers.get(i).move(moves.charAt(i));
        }
    }

    void printScoreboard() {
        try {
            buffer.write("~~ Results ~~");
            buffer.newLine();
            for (Player hero : allPlayers) {
                buffer.write(hero.toString());
                buffer.newLine();
            }
            buffer.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
