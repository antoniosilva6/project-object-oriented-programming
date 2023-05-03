package prr.app.terminals;

import prr.core.Network;
import prr.core.Terminal;

import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

  DoOpenMenuTerminalConsole(Network receiver) {
    super(Label.OPEN_MENU_TERMINAL, receiver);
    addStringField("id", Message.terminalKey());
    //FIXME add command fields
  }

  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    Terminal i = _receiver.getTerminal(id);

    if(i != null){
      (new prr.app.terminal.Menu(_receiver,i)).open();
    }

    else{
      throw new UnknownTerminalKeyException(id);
    }
  }
}
