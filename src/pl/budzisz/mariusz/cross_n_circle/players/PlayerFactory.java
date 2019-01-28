package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.view.Window;

public class PlayerFactory {

    public static Player getInstance(Figures type, Data data, boolean isOpponent, boolean isHuman, Window window, GameRules gameRules){
        if(isOpponent){
            if (isHuman){
                return getOpponentHumanInstance(type , data, window);
            } else {
                return getOpponentAiInstance(type , data, window, gameRules);
            }
        }
        else {
            if(isHuman){
                return getHumanInstance(type , data, window);
            } else {
                return getAiInstance(type, data, window, gameRules);
            }
        }
    }

    public static Player getAiInstance(Figures type, Data data, Window window, GameRules gameRules){
        Player instance;
        instance = new PlayerAI(type, data, window, gameRules);
        return instance;
    }
    public static Player getHumanInstance(Figures type, Data data, Window window){
        Player instance;
        instance = new PlayerHuman(type,data, window);
        return instance;
    }
    public static Player getOpponentHumanInstance(Figures type, Data data, Window window){
        Player instance;
        if (type.equals(Figures.CROSS)) {
            instance = new PlayerHuman(Figures.CIRCLE, data, window);
        } else {
            instance = new PlayerHuman(Figures.CROSS, data, window);
        }
        return instance;
    }
    public static Player getOpponentAiInstance(Figures type, Data data, Window window, GameRules gameRules){
        Player instance;
        if (type.equals(Figures.CROSS)) {
            instance = new PlayerAI(Figures.CIRCLE, data, window, gameRules);
        } else {
            instance = new PlayerAI(Figures.CROSS, data, window, gameRules);
        }
        return instance;
    }
}
