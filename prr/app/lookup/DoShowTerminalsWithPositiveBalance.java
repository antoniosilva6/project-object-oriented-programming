package prr.app.lookup;

import java.util.List;

import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

  DoShowTerminalsWithPositiveBalance(Network receiver) {
    super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {
    
    List<Terminal> list;
    list = _receiver.getAllTerminals();

    for(Terminal i:list){
      if(i.getPayments() > i.getDebt()){
        _display.addLine(i);
      }
    }
    _display.display();
  }
}
