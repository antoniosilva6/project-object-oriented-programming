package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("idFriend", Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    String idFriend = stringField("idFriend");

    Terminal i = _network.getTerminal(idFriend);

    if(i == null){
      throw new UnknownTerminalKeyException(idFriend);
    }
    
    Terminal j = _receiver.getFriend(idFriend);
    if(j == null){
      _receiver.addFriend(i);
    }
  }
}

