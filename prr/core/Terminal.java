package prr.core;

import java.io.Serializable;
import prr.core.TerminalMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum TerminalMode {
	BUSY, SILENCE, IDLE, OFF 	
}

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

  /** Serial number for serialization. */
  	private static final long serialVersionUID = 202208091753L;
  	private final String _id;
	private final String _idClient;
	private int _debt;
	private int _payments;
	private int _balance;
	private String _type;
	private TerminalMode _mode;
	
	private List<Terminal> _friends;
	private List<Communication> _communicationsMade;
	private List<Communication> _communicationsReceived;
	
	/** Constructor */
	public Terminal(String id, String idClient) {
		_id = id;
		_idClient = idClient;
		_payments = 0;
		_debt = 0;
		
		_mode = TerminalMode.IDLE;
		
		_friends = new ArrayList<Terminal>();
		_communicationsMade = new ArrayList<Communication>();
		_communicationsReceived = new ArrayList<Communication>();
	}
	
	/** Methods */
	
	public String getId(){
		return _id;
	}
	
	public String getIdClient(){
		return _idClient;
	}
	
	public int getDebt(){
		return _debt;
	}
	
	public void incrementDebt(int newDebt){
		_debt += newDebt;
	}

	public void decrementDebt(int newDebt){
		_debt -= newDebt;
	}
	
	public int getPayments(){
		return _payments;
	}
	
	public void incrementPayments(int newPayments){
		_payments += newPayments;
	}
	
	public int getBalance(){
		return _balance;
	}
	
	public TerminalMode getMode(){
		return _mode;
	}

	public String toStringMode(){
		if(this.getMode() == TerminalMode.OFF){
			return "OFF";
		}
		if(this.getMode() == TerminalMode.IDLE){
			return "IDLE";
		}
		if(this.getMode() == TerminalMode.SILENCE){
			return "SILENCE";
		}
		return "BUSY";
	}
	
	public void setOnIdle(){
		_mode = TerminalMode.IDLE;
	}

	public void setOnSilent(){
		_mode = TerminalMode.SILENCE;
	}

	public void turnOff(){
		_mode = TerminalMode.OFF;
	}

	public void setOnBusy(){
		_mode = TerminalMode.BUSY;
	}
	
	public List<Terminal> getAllFriends(){
		return Collections.unmodifiableList(_friends);
	}

	public Terminal getFriend(String id){
		for(Terminal i: _friends){
			if(id.equals(i.getId())){
				return i;
			}
		}
		return null;
	}

	public void addFriend(Terminal t){
		_friends.add(t);
	}

	public void removeFriend(Terminal t){
		_friends.remove(t);
	}

	public List<Communication> getAllCommsMade(){
		return Collections.unmodifiableList(_communicationsMade);
	}

	public Communication getCommunicationMade(int id){
		for(Communication i: _communicationsMade){
			if(id == i.getId()){
				return i;
			}
		}
		return null;
	}

	public void addCommunicationMade(Communication c){
		_communicationsMade.add(c);
	}

	public List<Communication> getAllCommsReceived(){
		return Collections.unmodifiableList(_communicationsReceived);
	}

	public Communication getCommunicationReceived(int id){
		for (Communication i: _communicationsReceived){
			if(id == i.getId()){
				return i;
			}
		}
		return null;
	}

	public void addCommunicationReceived(Communication c){
		_communicationsReceived.add(c);
	}

	public void setType(String type){
		_type = type;
	}
	
	public String getType(){
		return _type;
	}
	
	@Override
	public String toString(){
		if(_friends.isEmpty()) {
			return getType() + "|" + getId() + "|" + getIdClient() + "|" + getMode().toString() + "|" + getPayments() + "|" + getDebt();  
		} else {
			return getType() + "|" + getId() + "|" + getIdClient() + "|" + getMode().toString() + "|" + getPayments() + "|" + getDebt() + "|" + friendsToString();
		}
  	}
  
  	public String friendsToString(){
  		String s = "";
  		for(Terminal t: _friends){
  			s = s + "," + t.getId();
  		}
		s = s.replaceFirst(",", "");
  		return s;
  	}
  
    /**
   	* Checks if this terminal can end the current interactive communication.
   	*
   	* @return true if this terminal is busy (i.e., it has an active interactive communication) and
   	*          it was the originator of this communication.
   	**/
  	public boolean canEndCurrentCommunication() {
		for(Communication c: _communicationsMade){
			if(_mode == TerminalMode.BUSY && c.getIsOnGoing()){
				return true;
			}
		}
    	return false;
  	}
  
  	/**
   	* Checks if this terminal can start a new communication.
   	*
   	* @return true if this terminal is neither off neither busy, false otherwise.
   	**/
  	public boolean canStartCommunication() {
  		if (_mode == TerminalMode.IDLE || _mode == TerminalMode.SILENCE){
  			return true;
  			}
  		return false;
	}
}

