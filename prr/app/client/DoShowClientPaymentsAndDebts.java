package prr.app.client;

import prr.core.Network;
import prr.core.Client;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

  DoShowClientPaymentsAndDebts(Network receiver) {
    super(Label.SHOW_CLIENT_BALANCE, receiver);
    addStringField("id", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    Client c;
    c = _receiver.getClient(id);
    if(c == null){
      throw new UnknownClientKeyException(id);
    }
    _display.popup(Message.clientPaymentsAndDebts(id, c.getPayments(), c.getDebts()));
  }
}
