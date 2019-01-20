package pl.budzisz.mariusz.cross_n_circle.game_modes;

public enum Direction {
    LEFT (-1,0),
    RIGHT (1,0),
    UP (0,-1),
    DOWN(0,1),
    LEFT_UP(-1,-1),
    RIGHT_UP(1,-1),
    LEFT_DOWN(-1,1),
    RIGHT_DOWN(1,1);

    int x;
    int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
