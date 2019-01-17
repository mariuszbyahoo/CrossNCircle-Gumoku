package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

public class GumokuRules extends GameRules {

    public GumokuRules(Data data) {
        super(data);
    }

    public void checkEnd() {
        for (int i = 0; i < data.tab.length  ; i ++){
            for (int j = 0; j < data.tab.length  ; j++){
                this.gameOver = checkDirection(i,j,Direction.UP) ||
                                checkDirection(i,j,Direction.DOWN) ||
                                checkDirection(i,j,Direction.LEFT) ||
                                checkDirection(i,j,Direction.RIGHT) ||
                                checkDirection(i,j,Direction.LEFT_DOWN) ||
                                checkDirection(i,j,Direction.LEFT_UP) ||
                                checkDirection(i,j,Direction.RIGHT_DOWN) ||
                                checkDirection(i,j,Direction.RIGHT_UP);
                if (this.gameOver){
                    return;
                }
            }
        }
    }
    private  boolean checkDirection(int x, int y, Direction direction){
        if(!data.tab[x][y].equals(Figures.EMPTY)){
            Figures source = data.tab[x][y];
            for(int i = 0; i<4;i++){
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
        return true;
    }
}
