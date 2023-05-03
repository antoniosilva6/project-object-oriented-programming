package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

  DoTurnOnTerminal(Network context, Terminal terminal) {
    super(Label.POWER_ON, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {

    boolean verifications = true;

    if((_receiver.toStringMode()).equals("BUSY")){
      verifications = false;
    }

    else{
      if((_receiver.toStringMode()).equals("IDLE")){
      _display.addLine(Message.alreadyOn());
      verifications = false;
    }

    if(verifications){
      if((_receiver.toStringMode()).equals("OFF")){
        String id = _network.getFailedCommunication(_receiver.getId());
        if(id != null){
          _network.createNotification(id, "O2I");
        }
      }
      else{
        if((_receiver.toStringMode()).equals("BUSY")){
          String id = _network.getFailedCommunication(_receiver.getId());
          if(id != null){
            _network.createNotification(id, "B2I");
          }
        }
      }
      _receiver.setOnIdle();
    }
    _display.display();
    }
  }
}
