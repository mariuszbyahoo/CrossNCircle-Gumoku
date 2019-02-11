package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;

public class PlayerFactory {

    public static Player getInstance(Figures type, Data data, boolean isOpponent, boolean isHuman, GameRules gameRules) {
        if(isOpponent){
            if (isHuman){
                return getOpponentHumanInstance(type, data);
            } else {
                return getOpponentAiInstance(type, data, gameRules);
            }
        }
        else {
            if(isHuman){
                return getHumanInstance(type, data);
            } else {
                return getAiInstance(type, data, gameRules);
            }
        }
    }

    public static Player getAiInstance(Figures type, Data data, GameRules gameRules) {
        Player instance;
        instance = new PlayerAI(type, data, gameRules);
        return instance;
    }

    public static Player getHumanInstance(Figures type, Data data) {
        Player instance;
        instance = new PlayerHuman(type, data);
        return instance;
    }

    public static Player getOpponentHumanInstance(Figures type, Data data) {
        Player instance;
        if (type.equals(Figures.CROSS)) {
            instance = new PlayerHuman(Figures.CIRCLE, data);
        } else {
            instance = new PlayerHuman(Figures.CROSS, data);
        }
        return instance;
    }

    public static Player getOpponentAiInstance(Figures type, Data data, GameRules gameRules) {
        Player instance;
        if (type.equals(Figures.CROSS)) {
            instance = new PlayerAI(Figures.CIRCLE, data, gameRules);
        } else {
            instance = new PlayerAI(Figures.CROSS, data, gameRules);
        }
        return instance;
    }
}