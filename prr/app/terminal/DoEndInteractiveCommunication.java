package prr.app.terminal;

import java.util.List;

import prr.core.Communication;
import prr.core.InterativeCommunication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
    addIntegerField("duration", Message.duration());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    int duration = integerField("duration");

    for(Communication c: _receiver.getAllCommsMade()){
      if(c.getIsOnGoing()){
        c.setIsOnGoing(false);
        c.setSize(duration);
    
        Terminal t = _network.getTerminal(c.getIdReceiver());
        t.setOnIdle();
        _receiver.setOnIdle();

        if(c.getType().equals("VOICE")){
          List<InterativeCommunication> voiceCommunications;
          voiceCommunications = _network.getAllVoiceCommunications();
          for(InterativeCommunication i: voiceCommunications){
            if(c.getId() == i.getId()){
              int costVoice = i.computeCost(i);
              _display.popup(Message.communicationCost(costVoice));
            }
          }
        }

        if(c.getType().equals("VIDEO")){
          List<InterativeCommunication> videoCommunications;
          videoCommunications = _network.getAllVideoCommunications();
          for(InterativeCommunication i: videoCommunications){
            if(c.getId() == i.getId()){
              int costVideo = i.computeCost(i);
              _display.popup(Message.communicationCost(costVideo));
            }
          }
        }
      }
    }
  }
}
