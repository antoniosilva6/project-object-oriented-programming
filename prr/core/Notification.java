package prr.core;

import prr.core.NotificationType;

enum NotificationType {
    O2S, O2I, B2S, B2I
}

public class Notification {
    private NotificationType _type;
    private String _idSender;

    public Notification(String idSender){
        _idSender = idSender;
    }

    public String getIdSender(){
        return _idSender;
    }

    public NotificationType getType(){
        return _type;
    }

    public void setType(String type){
        if(type.equals("O2S")){
            _type = NotificationType.O2S;
        }
        if(type.equals("O2I")){
            _type = NotificationType.O2I;
        }
        if(type.equals("B2S")){
            _type = NotificationType.B2S;
        }
        _type = NotificationType.B2I;
    }

    @Override
    public String toString(){
        //Format: tipo-de-notificacao|idTerminal
        return getType() + "|" + getIdSender();
    }
}
