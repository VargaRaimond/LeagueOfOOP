package com.Main;

import com.Heroes.Player;
import com.Heroes.PlayerType;

import java.util.List;

public class GameEngine {

    private List<Player> allPlayers;

    void computeRounds(GameInput input) {
        allPlayers = input.getPlayers();
        for (int k = 0; k < input.getRounds(); k++) {
            moveHeroes(input.getMovesAt(k));

            for (int i = 0; i < allPlayers.size() - 1; i++) {
                for (int j = i + 1; j < allPlayers.size(); j++) {
                    if (allPlayers.get(i).getCoordinates().equals(allPlayers.get(j).getCoordinates())) {
                        if (allPlayers.get(i).type.equals(PlayerType.Wizard)) {
                            allPlayers.get(i).takeDamage(allPlayers.get(j));
                            allPlayers.get(j).takeDamage(allPlayers.get(i));
                        } else {
                            allPlayers.get(j).takeDamage(allPlayers.get(i));
                            allPlayers.get(i).takeDamage(allPlayers.get(j));
                        }
                        System.out.println(allPlayers.get(i).getCurrentHp());
                        System.out.println(allPlayers.get(j).getCurrentHp());
                    }
                }
            }
        }
    }

    void moveHeroes(String moves) {
        for(int i = 0; i < allPlayers.size(); i++) {
            allPlayers.get(i).move(moves.charAt(i));
        }
    }
}
