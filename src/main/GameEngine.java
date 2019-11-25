package main;

import heroes.Player;
import heroes.PlayerType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public final class GameEngine {

    private List<Player> allPlayers;

    public void computeRounds(GameInput input, String out) {
        allPlayers = input.getPlayers();

        for (int k = 0; k < input.getRounds(); k++) {
            moveHeroes(input.getMovesAt(k));

            for (Player hero : allPlayers) {
                if (hero.currentDotDamage != 0 || hero.currentDotDuration != 0) {
                    hero.setCurrentHp(hero.getCurrentHp() - hero.currentDotDamage);
                    hero.currentDotDuration--;
                    if (hero.currentDotDuration == 0) {
                        hero.currentDotDamage = 0;
                    }
                }
            }

            for (int i = 0; i < allPlayers.size() - 1; i++) {
                for (int j = i + 1; j < allPlayers.size(); j++) {
                    if (allPlayers.get(i).getCoordinates().equals(allPlayers.get(j).getCoordinates())) {
                        if (allPlayers.get(i).getCurrentHp() > 0 && allPlayers.get(j).getCurrentHp() > 0) {
                            if (allPlayers.get(i).type.equals(PlayerType.Wizard)) {
                                allPlayers.get(i).takeDamage(allPlayers.get(j));
                                allPlayers.get(j).takeDamage(allPlayers.get(i));
                            } else {
                                allPlayers.get(j).takeDamage(allPlayers.get(i));
                                allPlayers.get(i).takeDamage(allPlayers.get(j));
                            }
                            checkExpAndLevelUp(allPlayers.get(i), allPlayers.get(j));
                        }
                    }
                }
                if (allPlayers.get(i).currentDotDuration == 0) {
                    allPlayers.get(i).stunned = false;
                }
            }
        }
        printScoreboard(out);
    }

    void checkExpAndLevelUp(Player p1, Player p2) {
        if (p1.getCurrentHp() <= 0) {
            p2.setXp(p2.getXp() + Math.max(0, 200 - (p2.getLevel() - p1.getLevel()) * 40));
        }
        if (p2.getCurrentHp() <= 0) {
            p1.setXp(p1.getXp() + Math.max(0, 200 - (p1.getLevel() - p2.getLevel()) * 40));
        }
        if (p1.getCurrentHp() > 0) {
            while (p1.getXp() >= 250 + p1.getLevel() * 50) {
                p1.setLevel(p1.getLevel() + 1);
                p1.setMaxHp(p1.getMaxHp() + p1.hpScalePerLevel);
                p1.setCurrentHp(p1.getMaxHp());
            }
        }

        if (p2.getCurrentHp() > 0) {
            while (p2.getXp() >= 250 + p2.getLevel() * 50) {
                p2.setLevel(p2.getLevel() + 1);
                p2.setMaxHp(p2.getMaxHp() + p2.hpScalePerLevel);
                p2.setCurrentHp(p2.getMaxHp());
            }
        }

    }

    void moveHeroes(String moves) {
        for (int i = 0; i < allPlayers.size(); i++) {
            allPlayers.get(i).move(moves.charAt(i));
        }
    }

    void printScoreboard(String out) {
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
