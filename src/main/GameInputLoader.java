package main;

import angels.Angel;
import angels.AngelFactory;
import heroes.Player;
import heroes.PlayerFactory;
import map.Map;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public final class GameInputLoader {
        private final String mInputPath;

        public GameInputLoader(final String inputPath) {
            mInputPath = inputPath;
        }

         GameInput load() {

            List<Player> playersOrder = new ArrayList<>();
            List<String> roundMoves = new ArrayList<>();
             List<List<Angel>> angels = null;

             try {
                File in = new File(mInputPath);
                Scanner sc = new Scanner(in);

                int row = sc.nextInt();
                int col = sc.nextInt();

                List<List<Character>> mapHelper = new ArrayList<>();

                for (int i = 0; i < row; i++) {
                    mapHelper.add(new ArrayList<>());
                    // extract one row and then iterate through it
                    String oneRow = sc.next();
                    for (int j = 0; j < col; j++) {
                        mapHelper.get(i).add(oneRow.charAt(j));
                    }
                }

                Map.getInstance(row, col, mapHelper);

                int nrPlayers = sc.nextInt();
                PlayerFactory playerFactory = PlayerFactory.getInstance();

                for (int i = 0; i < nrPlayers; i++) {
                    Character type = sc.next().charAt(0);
                    Point coordinates = new Point(sc.nextInt(), sc.nextInt());
                    playersOrder.add(playerFactory.createHero(type, coordinates, i));
                }
                int nrRounds = sc.nextInt();

                for (int i = 0; i < nrRounds; i++) {
                    // get a full row of moves
                    roundMoves.add(sc.next());
                }

                angels = new ArrayList<>();
                AngelFactory angelFactory = AngelFactory.getInstance();
                for (int i = 0; i < nrRounds; i++) {
                    angels.add(new ArrayList<>());
                    int nrAngels = sc.nextInt();

                    for (int angelIt = 0; angelIt < nrAngels; angelIt++) {
                        // separate each angel from their position
                        sc.useDelimiter(",");
                        sc.skip(" ");
                        String tempAngel = sc.next();
                        int xAngel = sc.nextInt();
                        // go back to space delimiter and jump over the last comma
                        sc.reset();
                        sc.skip(",");
                        int yAngel = sc.nextInt();
                        Point angelPosition = new Point(xAngel, yAngel);
                        angels.get(i).add(angelFactory.createAngel(tempAngel, angelPosition));
                    }
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }


            return new GameInput(playersOrder, roundMoves, angels);
        }
    }

