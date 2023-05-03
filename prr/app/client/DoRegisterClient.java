package prr.app.client;

import prr.core.Network;
import prr.core.Client;
import prr.app.exception.DuplicateClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;
import java.util.List;


/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    addStringField("id", Message.key());
    addStringField("name", Message.name());
    addIntegerField("taxnumber", Message.taxId());
  }
  
  @Override
  protected final void execute() throws CommandException, DuplicateClientKeyException {
    List<Client> list;
    list = _receiver.getAllClients();
    
    String id = stringField("id");
    
    for(Client i: list){
    	if(i.getId().equals(id)){
    		throw new DuplicateClientKeyException(id);
      }
    }
    
    String name = stringField("name");
    Integer taxnumber = integerField("taxnumber");
  
    try {
      _receiver.registerClient(id, name, taxnumber);  
    } catch (IOException e) {
      throw new DuplicateClientKeyException(id);
    }
  }
}

