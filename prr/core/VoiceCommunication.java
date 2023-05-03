package prr.core;

public class VoiceCommunication extends InterativeCommunication {

    public VoiceCommunication(Terminal from, String idReceiver){
        super(from, idReceiver, "VOICE");
    }
}
