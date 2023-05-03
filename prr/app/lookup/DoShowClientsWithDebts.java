package prr.app.lookup;

import java.util.List;

import prr.core.Client;
import prr.core.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

  DoShowClientsWithDebts(Network receiver) {
    super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    
    List<Client> list = _receiver.getAllClients();
      
    for(Client i: list){
      if(i.getDebts() > 0){
        _display.addLine(i);
      }
    }
    _display.display();
  }
}

