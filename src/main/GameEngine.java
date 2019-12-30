package main;

import angels.Angel;
import common.Constants;
import heroes.Player;
import heroes.PlayerType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public final class GameEngine {

    private List<Player> allPlayers;
    private List<List<Angel>> allAngels;

    public void computeRounds(final GameInput input, final String out) {
        allPlayers = input.getPlayers();
        allAngels = input.getAngels();
        for (int roundNr = 0; roundNr < input.getRounds(); roundNr++) {
            moveHeroes(input.getMovesAt(roundNr));

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
            computeFights();
            if (!(allAngels.get(roundNr).isEmpty())) {
                for (Angel angel : allAngels.get(roundNr)) {
                    for (Player hero : allPlayers) {
                        if (hero.getPosition().equals(angel.getPosition())) {
                            hero.accept(angel);
                        }
                    }
                }
            }
        }
        printScoreboard(out);
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
        if (!p1.isAlive()) {
            p2.setXp(p2.getXp() + Math.max(Constants.NO_XP, Constants.BASE_KILL_XP
                    - (p2.getLevel() - p1.getLevel()) * Constants.LVL_DIF_MULTIP));
        }
        if (!p2.isAlive()) {
            p1.setXp(p1.getXp() + Math.max(Constants.NO_XP, Constants.BASE_KILL_XP
                    - (p1.getLevel() - p2.getLevel()) * Constants.LVL_DIF_MULTIP));
        }
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

    void printScoreboard(final String out) {
        try {
            FileWriter fout = new FileWriter(out);
            BufferedWriter buffer = new BufferedWriter(fout);
            for (Player hero : allPlayers) {
                buffer.write(hero.toString());
                buffer.newLine();
                System.out.println(hero);
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
