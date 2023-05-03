package prr.app.client;

import prr.core.Network;
import prr.core.Notification;
import prr.core.Client;

import java.util.List;

import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

  DoShowClient(Network receiver) {
    super(Label.SHOW_CLIENT, receiver);
    addStringField("id", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String id = stringField("id");
    Client i = _receiver.getClient(id);
    if(i == null){
      throw new UnknownClientKeyException(id);
    }

    Client c = _receiver.getClient(id);
    List<Notification> list = c.getAllNotifications(); 

    try {
    _display.popup(c);
    _display.popup(list);
    c.clearNotifications();
    throw new UnknownClientKeyException(id);
    } catch (UnknownClientKeyException e) {
    }
  }
}
