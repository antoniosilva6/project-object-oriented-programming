package prr.app.terminals;

import prr.core.Client;
import prr.core.Network;
import prr.core.Terminal;

import java.io.IOException;
import java.util.List;

import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("id", Message.terminalKey());
    addOptionField("type", Message.terminalType(), "BASIC","FANCY");
    addStringField("idClient", Message.clientKey());
  
  }

  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    String type = stringField("type");
    String idClient = stringField("idClient");
    
    int len = id.length();
    if(len != 6){
    	throw new InvalidTerminalKeyException(id); 
    }

    for(int i = 0; i < id.length(); i++){
      char c = id.charAt(i);
      if(!(Character.isDigit(c))){
        throw new InvalidTerminalKeyException(id);
      }
    }

    List<Terminal> list;
    list = _receiver.getAllTerminals();

    for(Terminal i: list){
      if(i.getId().equals(id)){
        throw new DuplicateTerminalKeyException(id);
      }
    }

    Client i = _receiver.getClient(idClient);
    if(i == null){
      throw new UnknownClientKeyException(idClient);
    }

    try {
      _receiver.registerTerminal(type, id, idClient);
    } catch (IOException e) {
      throw new InvalidTerminalKeyException(id);
    }
  }
}


    