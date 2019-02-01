package pl.budzisz.mariusz.cross_n_circle.game_modes;

public enum GameStatus {
    IN_PROGRESS("In progress"),
    X_WON("X Won!"),
    O_WON("O Won!"),
    DRAW("Draw");

    private String desc;

    GameStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
