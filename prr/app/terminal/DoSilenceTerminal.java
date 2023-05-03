package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

  DoSilenceTerminal(Network context, Terminal terminal) {
    super(Label.MUTE_TERMINAL, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    if((_receiver.toStringMode()).equals("BUSY")){
      _receiver.setOnBusy();
    }
    else{
      if((_receiver.toStringMode()).equals("SILENCE")){
      _display.addLine(Message.alreadySilent());
    }
    _receiver.setOnSilent();
    _display.display();
    }
  }
}
