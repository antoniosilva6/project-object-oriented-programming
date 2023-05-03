package prr.core;

public class VideoCommunication extends InterativeCommunication{
    
    public VideoCommunication(Terminal from, String idReceiver){
        super(from, idReceiver, "VIDEO");
    }
}
