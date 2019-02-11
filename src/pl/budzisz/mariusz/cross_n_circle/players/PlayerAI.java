package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.CrossNCircleGame;
import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.Direction;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameStatus;

import javax.swing.*;
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
        if (data.isPossible2move()) {
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
                    /** W else
                     * zawarta jest logika postepowania komputera w przypadku podejmowania decyzji ws tego co robic dalej.
                     * 1 -> Sprawdz czy mozesz zakonczyc gre jednym ruchem, jak nie, to przywroc puste pole w
                     *      Gdzie to sprawdzales.(linijki 50-58 )
                     * 2-> Jesi nie, to wtedy sprawdz, czy jak przeciwnik tam postawi figure to przegrasz
                     *      Jesli tak, to postaw tam swoja. (linijki 59-65)
                     * 3-> Jesli nie mozesz 1 ruchem zakonczyc gry ani nie jestes tym zagrozony ze strony przeciwnika to
                     *      Zmien sprawdzane pole na puste. (linijki 66-68)
                     */
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

        } else {
            System.out.println("Nie da się wykonać ruchu...");
            CrossNCircleGame.gameStatus = GameStatus.DRAW;
            System.out.println(GameStatus.DRAW.getDesc());
            this.gameRules.gameOver = true;
        }
    }


    /**
     * Tego typu komentarze są to JavaDocs, które są komentarzami ukazujące się także na poziomie
     * wywoływania metod, wpisywania np. checkDirec(I tutaj powinna się pojawić podpowiedź, po podkreśleniu
     * której po prawej pojawi się Javadoc w chmurce koło IntelliSense.
     */


    /**
     * Mamy tutaj metode checkDirection() ktora ma sprawdzic czy w danym kierunku wystepuja 3 takie same znaki
     * No i bierze czwarte pole z kolei i jego wspolrzedne zapisuje do possibleX i possibleY
     * czyt. potem wstawia w tym polu swoj znak.
     * @param x współrzędna x
     * @param y współrzędna y
     * @param direction kierunek weryfikacji sprawdzania końca gry
     * @return zwraca prawdę jeśli gra się zakończyła w tym miejscu, false jeśli się nie zakończyła.
     */

    private  boolean checkDirection(int x, int y, Direction direction) {
        if (!data.tab[x][y].equals(Figures.EMPTY)) {
            if (data.tab[x][y].equals(foesFigure)) {
                Figures source = data.tab[x][y];
                for (int i = 0; i < 2; i++) {
                    x = x + direction.getX();
                    y = y + direction.getY();
                    if (x < 0 || y < 0 || x > data.tab.length - 1 || y > data.tab.length - 1) {
                        return false;
                    }
                    if (data.tab[x][y].equals(Figures.EMPTY)) {
                        return false;
                    } else if (!data.tab[x][y].equals(source)) {
                        return false;
                    }
                }
                possibleX = x + direction.getX();
                possibleY = y + direction.getY();
                if (possibleX < 1 || possibleY < 1 || possibleX > data.tab.length - 2 || possibleY > data.tab.length - 2) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * No i mamy wersje Alpha sztucznej inteligencji gdzie komputer potrafi juz samemu kontratakowac, problemy tu sa dwa:
     * 1-> gdy kontratakuje, to wtedy nadal wstawia jeszcze jedna figure.
     * ZROBIONE.
     * 2-> w przypadku gry w kolko i krzyzyk potrafi sie odwolac do elementu tablicy spoza jej zakresu, ArrayIndexOutOfBoundException.
     * ZROBIONE
     * 3-> w przypadku kontrataku komputer stara sie zablokowac ruch czlowieka z kazdego kierunku, nawet jesli w tym kierunku pozostalo
     * tylko jedno wolne pole, czyt. nic to nie da jak je zajmie.
     * ZROBIONE.
     */

    public void checkCounterAttack() {
        for (int i = 0; i < data.tab.length; i++) {
            for (int j = 0; j < data.tab.length; j++) {
                for (Direction direction : Direction.values()) {
                    if (checkDirection(i, j, direction)) {
                        if (counterAttacked) {
                            continue;
                        } else {
                            counterAttack(possibleX, possibleY, data);
                        }
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
    public Figures getFigure(){
        return figure;
    }
}