package prr.app.client;

import prr.core.Network;
import prr.core.Client;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("id", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    Client c;
  	c = _receiver.getClient(id);
    if(c == null){
      throw new UnknownClientKeyException(id);
    } else{
        if(c.getReceiveNotifications() == true){
        c.setReceiveNotifications(false);
      } else {
        _display.popup(Message.clientNotificationsAlreadyDisabled());
      }
    }
	}
}	
