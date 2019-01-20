package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.Direction;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;

import java.util.Random;

import static pl.budzisz.mariusz.cross_n_circle.Data.round;

public class PlayerAI extends Player {

    GameRules gameRules;
    private int possibleX ;
    private int possibleY ;
    private boolean counterAttacked = false;

    Figures foesFigure = figure.equals(Figures.CROSS) ? Figures.CIRCLE : Figures.CROSS;
    public PlayerAI (Figures figure , Data data, GameRules gameRules) {
        super(figure , data);
        this.gameRules = gameRules;
    }

    @Override
    public void move() {
        checkCounterAttack();
        if (counterAttacked == false) {
            if (round == 2) {
                if (data.tab[0][0].equals(Figures.EMPTY)) {
                    data.tab[0][0] = figure;
                } else if (data.tab[data.tab.length - 1][data.tab.length - 1].equals(Figures.EMPTY)) {
                    data.tab[data.tab.length - 1][data.tab.length - 1] = figure;
                } else if (data.tab[0][data.tab.length - 1].equals(Figures.EMPTY)) {
                    data.tab[0][data.tab.length - 1] = figure;
                } else if (data.tab[data.tab.length - 1][0].equals(Figures.EMPTY)) {
                    data.tab[data.tab.length - 1][0] = figure;
                }
            } else {
                for (int i = 0; i < data.tab.length; i++) {
                    for (int j = 0; j < data.tab[i].length; j++) {
                        if (data.tab[i][j].equals(Figures.EMPTY)) {
                            data.tab[i][j] = figure;
                            gameRules.checkEnd();
                            if (this.gameRules.gameOver) {
                                this.gameRules.gameOver = false;
                                return;
                            } else {
                                data.tab[i][j] = Figures.EMPTY;
                            }
                            data.tab[i][j] = foesFigure;
                            gameRules.checkEnd();
                            if (this.gameRules.gameOver) {
                                data.tab[i][j] = figure;
                                gameRules.checkEnd();
                                this.gameRules.gameOver = false;
                                return;
                            } else {
                                data.tab[i][j] = Figures.EMPTY;
                            }
                        }
                    }
                }


                while (true) {
                    Random rn = new Random();
                    int x = rn.nextInt(data.tab.length);
                    int y = rn.nextInt(data.tab.length);
                    if (data.tab[y][x].equals(Figures.EMPTY)) {
                        data.tab[y][x] = figure;
                        break;
                    }
                }
            }
        }
        counterAttacked = false;
    }

    /**
     * Mamy tutaj metode checkDirection() ktora ma sprawdzic czy w danym kierunku wystepuja 3 takie same znaki
     * No i teraz potrzeba zeby ta metoda brala czwarty znak z kolei i jego wspolrzedne zapisywala do pol
     * O nazwach possibleX i possibleY
     * @param x
     * @param y
     * @param direction
     * @return
     */

    private  boolean checkDirection(int x, int y, Direction direction){
        if(!data.tab[x][y].equals(Figures.EMPTY)){
            Figures source = data.tab[x][y];
            for(int i = 0; i<2;i++){
                x = x + direction.getX();
                y = y + direction.getY();
                if(x < 0 || y < 0 || x > data.tab.length - 1 || y > data.tab.length - 1){
                    return false;
                }
                if (data.tab[x][y].equals(Figures.EMPTY)){
                    return false;
                } else if(!data.tab[x][y].equals(source)){
                    return false;
                }
            }
        } else {
            return false;
        }
        possibleX = x + direction.getX();
        possibleY = y + direction.getY();
        return true;
    }

    /**
     * No i mamy wersje, w bardzo Alpha sztucznej inteligencji gdzie komputer potrafi juz samemu kontratakowac, problemy tu sa dwa:
     * 1-> gdy kontratakuje, to wtedy nadal wstawia jeszcze jedna figure.
     * 2-> w przypadku gry w kolko i krzyzyk potrafi sie odwolac do elementu tablicy spoza jej zakresu, ArrayIndexOutOfBoundException.
     * 3-> w przypadku kontrataku komputer stara sie zablokowac ruch czlowieka z kazdego kierunku, nawet jesli w tym kierunku pozostalo
     * tylko jedno wolne pole, czyt. nic to nie da jak je zajmie.
     */

    public void checkCounterAttack() {
        for (int i = 0; i < data.tab.length; i++) {
            for (int j = 0; j < data.tab.length; j++) {
                if (checkDirection(i, j, Direction.UP)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.DOWN)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.LEFT)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.RIGHT)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.LEFT_DOWN)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.LEFT_UP)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.RIGHT_DOWN)) {
                    if (counterAttacked) {
                        break;
                    }
                    counterAttack(possibleX, possibleY, data);
                } else if (checkDirection(i, j, Direction.RIGHT_UP)) {
                    if (counterAttacked) {
                        break;
                    }
                }
            }
        }
    }

    private void counterAttack(int possibleX, int possibleY, Data data) {
        if(data.tab[possibleX][possibleY] == Figures.EMPTY) {
            data.tab[possibleX][possibleY] = figure;
            counterAttacked = true;
            System.out.println("Komputer kontratakuje!");
        }
    }
}
