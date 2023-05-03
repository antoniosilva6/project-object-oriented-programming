package prr.app.main;

import java.io.IOException;

import prr.core.NetworkManager;
import prr.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to save a file.
 */

class DoSaveFile extends Command<NetworkManager> { 

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver); 
  }
  
  @Override
  protected final void execute() { 
    try {
      _receiver.save();
    } catch (IOException | MissingFileAssociationException e1) {
      try {
        String filename = Form.requestString(Message.newSaveAs()); 
        _receiver.saveAs(filename);
      } catch (IOException | MissingFileAssociationException e2){
         e2.printStackTrace();
      }
    }
  }
}
