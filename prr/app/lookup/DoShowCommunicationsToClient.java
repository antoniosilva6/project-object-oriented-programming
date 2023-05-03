package prr.app.lookup;

import java.util.List;

import prr.app.exception.UnknownClientKeyException;
import prr.core.Client;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

  DoShowCommunicationsToClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
    addStringField("idClient", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    
    String idClient = stringField("idClient");

    Client j = _receiver.getClient(idClient);
    if(j == null){
      throw new UnknownClientKeyException(idClient);
    }

    List<Terminal> list;
    list = _receiver.getAllTerminals();

    for(Terminal i: list){
      if(i.getIdClient().equals(idClient)){
        _display.popup(i.getAllCommsReceived());
      }
    }
  }
}
