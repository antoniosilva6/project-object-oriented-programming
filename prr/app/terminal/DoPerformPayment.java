package prr.app.terminal;

import prr.core.Communication;
import prr.core.Network;
import prr.core.Terminal;
import pt.tecnico.uilib.menus.CommandException;
// Add more imports if needed

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

  DoPerformPayment(Network context, Terminal terminal) {
    super(Label.PERFORM_PAYMENT, context, terminal);
    addIntegerField("idComm", Message.commKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    
    int idComm = integerField("idComm");
    Communication c =_network.getCommunication(idComm);

    if(c.getIdSender().equals(_receiver.getId())){
      c.setIsPaid(true);
      _receiver.incrementPayments(c.getCost());
      _receiver.decrementDebt(c.getCost());
    }
    
    //_display.addLine(Message.foreignCommunication);
    
    //FIXME implement command
  }
}
