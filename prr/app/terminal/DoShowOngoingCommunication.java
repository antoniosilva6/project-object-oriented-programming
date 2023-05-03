package prr.app.terminal;

import java.util.List;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    List<Communication> commsMade = _receiver.getAllCommsMade();
    List<Communication> commsReceived = _receiver.getAllCommsReceived();
    Boolean verifications = true;

    for(Communication i: commsMade){
      if(i.getIsOnGoing()){
        _display.popup(i);
        verifications = false;
      }
    }
    for(Communication c: commsReceived){
      if(c.getIsOnGoing()){
        _display.popup(c);
        verifications = false;
      }
    }
    if(verifications){
      _display.popup(Message.noOngoingCommunication());
    }
  }
}
