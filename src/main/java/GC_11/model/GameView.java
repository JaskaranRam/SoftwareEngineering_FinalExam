package GC_11.model;


import GC_11.model.common.CommonGoalCard;

import java.io.Serializable;
import java.util.List;

/**
 * This class give all the information about the Game specialized for a particular Player given by parameter
 * Use marker 'transiet' if we don't want to serialize the attribute
 */
public class GameView implements Serializable {

    //The serialization process at runtime associates an id with each Serializable class which is known as SerialVersionUID.
    //The sender and receiver must have the same SerialVersionUID, otherwise, InvalidClassException will be thrown when you deserialize the object.
    private static final long serialVersionUID = 2L;

    /** NON so ancora se servono
    public String playerNickname;
    public String PersonalGoalText;
    public String[] CommonGoalText;

    //From the first player in order
    public int[] playersPoints;
     */

    private boolean error;

    private String exceptionMessage;
    private final Game model;

    public GameView(Game model, Exception exception){
        //if (model == null){
        //    throw new IllegalArgumentException();
        //}
        if(exception != null){
            this.error = true;
            this.exceptionMessage = exception.getMessage();
        }
        this.model = model;
    }

    public Board getBoard() { return model.getBoard(); }

    public List<Player> getPlayers(){ return model.getPlayers(); }

    public Player getCurrentPlayer(){ return model.getCurrentPlayer(); }

    public List<CommonGoalCard> getCommonGoalCards(){
        return model.getCommonGoal();
    }

    public void setPersonalNull(Player player){
        for(Player p : this.getPlayers()){
            if(!p.equals(player)) p.setPersonalGoal(null);
        }
    }

    public CommonGoalCard getCommonGoalCard(int index) {
        return this.model.getCommonGoal(index);
    }

    public boolean isError() {
        return this.error;
    }

    public void setExceptionMessage(Exception e){
        this.exceptionMessage = e.getMessage();
    }

    public void setExceptionMessage(String mess){
        this.exceptionMessage = mess;
    }


    public String getExceptionMessage(){
        return this.exceptionMessage;
    }
}