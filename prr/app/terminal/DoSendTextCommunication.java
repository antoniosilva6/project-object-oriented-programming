package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.TextCommunication;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("idReceiver", Message.terminalKey());
    addStringField("message", Message.textMessage());
  }
  
  @Override 
  protected final void execute() throws CommandException {

    String idReceiver = stringField("idReceiver");
    String message = stringField("message");
    boolean verifications = true;

    Terminal i = _network.getTerminal(idReceiver);
    if(i == null){
      throw new UnknownTerminalKeyException(idReceiver);
    }

    for(Terminal t: _network.getAllTerminals()){
      if(t.getId().equals(idReceiver)){
        if(t.toStringMode().equals("OFF")){
          _display.popup(Message.destinationIsOff(idReceiver));
          verifications = false;
        }
      }
    }

    if(verifications){
      TextCommunication tc = _network.sendTextCommunication(_receiver, idReceiver, message);
      tc.computeCost();
    }
  }
} 
