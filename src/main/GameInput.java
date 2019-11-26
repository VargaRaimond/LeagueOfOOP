package main;

import heroes.Player;

import java.util.List;

    public final class GameInput {
        private final List<String> mRoundMoves;
        private final List<Player> mPlayersOrder;
        private int mRounds;

        public GameInput() {
            mRoundMoves = null;
            mPlayersOrder = null;
            mRounds = -1;
        }

        public GameInput(final List<Player> players, final List<String> roundMoves) {
            mRoundMoves = roundMoves;
            mPlayersOrder = players;
            mRounds = roundMoves.size();
        }

         String getMovesAt(final int round) {
            return mRoundMoves.get(round);
        }

         List<Player> getPlayers() {
            return mPlayersOrder;
        }

         int getRounds() {
            return mRounds;
        }

    }
