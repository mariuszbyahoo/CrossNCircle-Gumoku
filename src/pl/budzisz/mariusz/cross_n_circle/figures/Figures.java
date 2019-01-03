package pl.budzisz.mariusz.cross_n_circle.figures;

public enum Figures {
    EMPTY(" "),
    CROSS("X"),
    CIRCLE("O");

    private String symbol;

    Figures(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
