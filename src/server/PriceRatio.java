package server;

enum PriceRatio {
    HIGH(1.2f),
    MEDIUM(1.0f),
    LOW(0.8f);

    private final float ratio;

    PriceRatio(float ratio) {
        this.ratio = ratio;
    }

    public float getValue() {
        return ratio;
    }
}
