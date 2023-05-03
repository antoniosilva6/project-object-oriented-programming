package prr.app.lookup;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;

import java.util.List;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

  DoShowUnusedTerminals(Network receiver) {
    super(Label.SHOW_UNUSED_TERMINALS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    List<Terminal> list = _receiver.getAllTerminals();
    List<Communication> commsMade;
    List<Communication> commsReceived;

    for(Terminal i: list){
      commsMade = i.getAllCommsMade();
      commsReceived = i.getAllCommsReceived();
      if(commsMade.isEmpty() && commsReceived.isEmpty()){
        _display.addLine(i);  
      }
    }
    _display.display();
  }
}
