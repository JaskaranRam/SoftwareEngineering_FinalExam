package GC_11.view;

import GC_11.exceptions.ColumnIndexOutOfBoundsException;
import GC_11.network.choices.Choice;
import GC_11.network.message.GameViewMessage;
import GC_11.network.message.LobbyViewMessage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;

/**
 * Abstract class view, father of all the views,it defines the signature of all the method that the view require to print
 * all the necessary data during the Game
 */
public abstract class View implements PropertyChangeListener {

    protected String nickname;
    protected boolean inGame = true;
    protected GameViewMessage modelView;
    protected LobbyViewMessage lobbyView;
    protected PropertyChangeListener listener;

    public abstract void run();
    public abstract void show();
    public abstract Choice getPlayerChoice();
    public abstract void askNickname();
    public abstract void askMaxNumber();
    public abstract void printLobby(LobbyViewMessage lobbyViewMessage);
    public abstract String getNickname();
    public abstract void setModelView(GameViewMessage modelView);

    public abstract void propertyChange(PropertyChangeEvent evt);
    public void setListener(PropertyChangeListener listener) {
        this.listener = listener;
    }
    public PropertyChangeListener getListener() {
        return this.listener;
    }
}
