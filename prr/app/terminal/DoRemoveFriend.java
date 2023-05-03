package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
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
    if(j != null){
      _receiver.removeFriend(i);
    }
  }
}
