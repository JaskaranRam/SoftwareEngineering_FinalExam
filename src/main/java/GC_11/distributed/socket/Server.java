package GC_11.distributed.socket;

import java.beans.PropertyChangeEvent;
import java.rmi.Remote;
import java.rmi.RemoteException;

import GC_11.distributed.Client;
import GC_11.exceptions.*;
import GC_11.util.Choice;

public interface Server extends Remote {

    /**
     * Register a client to the server
     * @param client the client to register
     */
    void register(Client client) throws RemoteException;

    void register(GC_11.distributed.socket.Client client) throws RemoteException;

    /**
     * Notify the server that a client has made a choice
     * @param client  the client that generated the event
     * @param arg     the choice made by the client
     */
    void update (Client client, Choice arg) throws RemoteException, ColumnIndexOutOfBoundsException, ExceededNumberOfPlayersException, NotEnoughFreeSpacesException, NameAlreadyTakenException, IllegalMoveException;


}
