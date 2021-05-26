package me.godlycoder.rps.moves;

public abstract class Moves {
    public abstract boolean beats(Class<? extends Moves> move);

    public static class Rock extends Moves {

        @Override
        public boolean beats(Class<? extends Moves> move) {
            return move == Scissors.class;
        }
    }

    public static class Paper extends Moves {

        @Override
        public boolean beats(Class<? extends Moves> move) {
            return move == Rock.class;
        }
    }

    public static class Scissors extends Moves {

        @Override
        public boolean beats(Class<? extends Moves> move) {
            return move == Paper.class;
        }
    }
}
