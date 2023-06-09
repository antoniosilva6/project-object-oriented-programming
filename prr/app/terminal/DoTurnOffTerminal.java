package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

  DoTurnOffTerminal(Network context, Terminal terminal) {
    super(Label.POWER_OFF, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    if((_receiver.toStringMode()).equals("BUSY")){
      _receiver.setOnBusy();
    }
    else{
      if((_receiver.toStringMode()).equals("OFF")){
        _display.addLine(Message.alreadyOff());
      }
      _receiver.turnOff();
      _display.display();  
    }
  }
}
