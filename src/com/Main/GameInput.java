package com.Main;

import com.Heroes.Player;

import java.util.List;

    public class GameInput {
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

        public final String getMovesAt(int round) {
            return mRoundMoves.get(round);
        }

        public final List<Player> getPlayers() {
            return mPlayersOrder;
        }

        public final int getRounds() {
            return mRounds;
        }

        public final boolean isValidInput() {
            boolean membersInstantiated = mRoundMoves != null && mPlayersOrder != null;
            boolean membersNotEmpty = mRoundMoves.size() > 0 && mPlayersOrder.size() > 0 && mRounds > 0;

            return membersInstantiated && membersNotEmpty;
        }
    }
