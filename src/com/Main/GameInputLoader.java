package com.Main;

import com.Heroes.Player;
import com.Heroes.PlayerFactory;
import com.Map.Map;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import  java.util.Scanner;

    public final class GameInputLoader {
        private final String mInputPath;

        GameInputLoader(final String inputPath) {
            mInputPath = inputPath;
        }

        public GameInput load() {

            List<Player> playersOrder = new ArrayList<>();
            List<String> roundMoves = new ArrayList<>();

            try {
                File in = new File(mInputPath);
                Scanner sc = new Scanner(in);

                int row = sc.nextInt();
                int col = sc.nextInt();

                List<List<Character>> mapHelper = new ArrayList<>();

                for (int i = 0; i < row; i++) {
                    mapHelper.add(new ArrayList<>());
                    String oneRow = sc.next();
                    for (int j = 0; j < col; j++) {
                        mapHelper.get(i).add(oneRow.charAt(j));
                    }
                }

                Map instance = Map.getInstance(row, col, mapHelper);

                int nrPlayers = sc.nextInt();
                PlayerFactory playerFactory = PlayerFactory.getInstance();

                for (int i = 0; i < nrPlayers; i++) {
                    Character type = sc.next().charAt(0);
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    Point coordinates = new Point(x, y);
                    playersOrder.add(playerFactory.createHero(type, coordinates));
                }
                int nrRounds = sc.nextInt();

                for (int i = 0; i < nrRounds; i++) {
                    roundMoves.add(sc.next());
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            return new GameInput(playersOrder, roundMoves);
        }
    }

