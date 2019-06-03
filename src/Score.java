public enum Score {

    p0(0), p15(15), p30(30), p40(40), A(41);

    private final int score;

    Score(int score) {
        this.score = score;
    }

    public int getValue() {
        return score;
    }

    private static Score[] values = values();

    public Score next() {
        return values[(this.ordinal() + 1) % values.length];
    }
}
