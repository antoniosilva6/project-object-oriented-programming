package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("idReceiver", Message.terminalKey());
    addOptionField("type", Message.commType(), "VIDEO","VOICE");
  }
  
  @Override
  protected final void execute() throws CommandException {
    Boolean verifications = true;
    String idReceiver = stringField("idReceiver");
    String type = stringField("type");

    Terminal i = _network.getTerminal(idReceiver);

    if(i == null){
      throw new UnknownTerminalKeyException(idReceiver);
    }

    if(_receiver.getId() == idReceiver){
      verifications = false;
    }

    else{
      if(type.equals("VIDEO") && (_receiver.getType()).equals("BASIC")){
        _display.addLine(Message.unsupportedAtOrigin(_receiver.getId(), type));
        verifications = false;
      }
      
      else{
        if(type.equals("VIDEO") && (_receiver.getType()).equals("BASIC")){
          _display.addLine(Message.unsupportedAtOrigin(_receiver.getId(), type));
          verifications = false;
        }

        else{
          if(type.equals("VIDEO") && (i.getType()).equals("BASIC")){
          _display.addLine(Message.unsupportedAtDestination(idReceiver, type));
          verifications = false;
          }
    
          else{
            if(i.toStringMode().equals("OFF")){
              _network.addFailedCommunication(idReceiver);
              _display.addLine(Message.destinationIsOff(idReceiver));
              verifications = false;
            }
       
            else{
              if(i.toStringMode().equals("BUSY")){
                _network.addFailedCommunication(idReceiver);
                _display.addLine(Message.destinationIsBusy(idReceiver));
                verifications = false;
              }
        
              else{
                if(i.toStringMode().equals("SILENCE")){
                  _network.addFailedCommunication(idReceiver);
                  _display.addLine(Message.destinationIsSilent(idReceiver));
                  verifications = false;
                }
              }
            } 
          }
        }
      }
    }

    if(verifications){
      _network.startIterativeCommunication(_receiver, idReceiver, type);  
      _receiver.setOnBusy();
      i.setOnBusy();
    }

  _display.display(); 
  }
} 
