public class CrossNCircleGame {
    public static void main (String[]args){
        boolean game = true;
        Data data = new Data();
        while(game = true) {
            data.printTable();
            data.move("O");
            data.printTable();
            data.checkEnd();
            if(data.gameOver == true){
                break;
            }
            data.moveAi("X");
            data.printTable();
            data.checkEnd();
            if(data.gameOver == true){
                break;
            }
        }
    }
}
