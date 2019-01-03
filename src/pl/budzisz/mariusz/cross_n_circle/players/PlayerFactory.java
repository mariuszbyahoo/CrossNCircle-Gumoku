package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

public class PlayerFactory {
    public static Player getAiInstance(Figures type, Data data){
        Player instance;
        instance = new PlayerHuman(type, data);
        return instance;
    }
    public static Player getHumanInstance(Figures type, Data data){
        Player instance;
        instance = new PlayerHuman(type,data);
        return instance;
    }
    public static Player getOpponentHumanInstance(Figures type, Data data){
        Player instance;
        if (type.equals(Figures.CROSS)) {
            instance = new PlayerHuman(Figures.CIRCLE, data);
        } else {
            instance = new PlayerHuman(Figures.CROSS, data);
        }
        return instance;
    }
    public static Player getOpponentAiInstance(Figures type, Data data){
        Player instance;
        if (type.equals(Figures.CROSS)) {
            instance = new PlayerAI(Figures.CIRCLE, data);
        } else {
            instance = new PlayerAI(Figures.CROSS, data);
        }
        return instance;
    }
}
