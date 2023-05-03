package prr.core;

import java.io.Serializable;

abstract public class Communication implements Serializable {
    private static final long serialVersionUID = 202208091753L;
    private int _id;
    private int _cost;
    private boolean _isPaid;
    private int _size;
    private String _type;
    private Terminal _from;
    private String _idReceiver;
    private boolean _isOnGoing;
    private Client _client;
    private static int _counter = 0;

    public Communication(Terminal from, String idReceiver){
        _from = from;
        _idReceiver = idReceiver;

        _counter++;
        _id = _counter;
        _size = 0;
        _isPaid = false;
        _cost = 0;
        _isOnGoing = true;
        _client = null;
    }

    public int getId(){
        return _id;
    }

    public Terminal getTerminalFrom(){
        return _from;
    }

    public String getIdSender(){
        return _from.getId();
    }

    public String getIdReceiver(){
        return _idReceiver;
    }

    public String getType(){
        return _type;
    }

    public void setType(String type){
        _type = type;
    }

    public boolean getIsOnGoing(){
        return _isOnGoing;
    }

    public void setIsOnGoing(boolean isOnGoing){
        _isOnGoing = isOnGoing;
    }

    public boolean getIsPaid(){
        return _isPaid;
    }

    public void setIsPaid(boolean isPaid){
        _isPaid = isPaid;
    }

    public int getCost(){
        return _cost;
    }

    public void setCost(int cost){
        _cost = cost;
    }

    public int getSize(){
        return _size;
    }

    public void setSize(int size){
        _size = size;
    }

    public Client getClient(){
        return _client;
    }

    public void setClient(Client c){
        _client = c;
    }

    public String isOnGoingToString(){
        if (_isOnGoing){
            return "ONGOING";
        }
        return "FINISHED";
    }

    public String getIdClient(){
        return _from.getIdClient();
    }

    @Override
    public String toString(){
        //Format: type|idCommunication|idSender|idReceiver|units|price|status
        return getType() + "|" + getId() + "|" +  getIdSender() + "|" + getIdReceiver() + "|" + getSize() + "|" + getCost() + "|" + isOnGoingToString();
    }
}

