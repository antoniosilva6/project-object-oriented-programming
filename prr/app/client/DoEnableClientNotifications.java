package prr.app.client;

import prr.core.Network;
import prr.core.Client;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

  DoEnableClientNotifications(Network receiver) {
    super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
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
		if(c.getReceiveNotifications() == false){
			c.setReceiveNotifications(true);
		} else {
			_display.popup(Message.clientNotificationsAlreadyEnabled());
		}	
	}
  }
}
