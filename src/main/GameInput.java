package main;

import heroes.Player;
import angels.Angel;

import java.util.List;

    public final class GameInput {
        private final List<String> mRoundMoves;
        private final List<Player> mPlayersOrder;
        private int mRounds;
        private final List<List<Angel>> mAngels;

        public GameInput() {
            mRoundMoves = null;
            mPlayersOrder = null;
            mAngels = null;
            mRounds = -1;
        }

        public GameInput(final List<Player> players, final List<String> roundMoves,
                         final List<List<Angel>> angels) {
            mRoundMoves = roundMoves;
            mPlayersOrder = players;
            mRounds = roundMoves.size();
            mAngels = angels;
        }

        public List<List<Angel>> getAngels() {
            return mAngels;
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
