package prr.core;

import prr.core.ClientLevel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

enum ClientLevel {
    NORMAL, GOLD, PLATINUM;
}

public class Client implements Serializable {
    private final String _id;
    private final String _name;
    private final int _taxNumber;
    private boolean _receiveNotifications;
    private int _payments;
    private int _debt; 
    private ClientLevel _clientLevel;  
    
    /** Vetores */  
    private ArrayList<Terminal> _terminals;
	private ArrayList<Notification> _notifications;

	public Client(String id, String name, int taxNumber){
		_id = id;
		_name = name;
		_taxNumber = taxNumber;
		 
		_receiveNotifications = true;
		_payments = 0;
		_debt = 0;
		_clientLevel = ClientLevel.NORMAL;
			
		_terminals = new ArrayList<Terminal>();
		_notifications = new ArrayList<Notification>();
			
	}

	public String getName(){
		return _name;
	}

	public String getId(){
		return _id;
	}

	public int getTaxNumber(){
	  	return _taxNumber;
	}

	public ClientLevel getClientLevel(){
		return _clientLevel;
	}
	
	public boolean getReceiveNotifications(){
		return _receiveNotifications;
	}
	
	public void setReceiveNotifications(boolean receive){
		_receiveNotifications = receive;
	}
	
	public void changeLevel(ClientLevel newClientLevel){
		_clientLevel = newClientLevel;
	}

	public List<Terminal> getAllTerminals(){
		return Collections.unmodifiableList(_terminals);
	}

	public List<Notification> getAllNotifications(){
		return Collections.unmodifiableList(_notifications);
	}

	public void addNotifications(Notification n){
		_notifications.add(n);
	}

	public void clearNotifications(){
		_notifications.clear();
	}

	public int numberOfTerminals(){
		return _terminals.size();
	}
	
	public int getPayments(){
		return _payments;
	}

	public void incrementPayments(int payments){
		_payments += payments;
	}

	public int getDebts(){
		return _debt;
	}

	public void incrementDebt(int debt){
		_debt += debt;
	}

	public void decrementDebt(int debt){
		_debt -= debt;
	}


	public int getBalance(){
		return getPayments() - getDebts();
	}
	
	public void addTerminal(Terminal t){
		_terminals.add(t);
	}

	public String notificationsToString(){
		if(!_receiveNotifications){
			return "NO";
		}
		return "YES";
	}

	@Override
	public String toString(){
		//Format: CLIENT|key|name|taxId|type|notifications|terminals|payments|debts
		return "CLIENT|" + _id + "|" + _name + "|" + _taxNumber + "|" + _clientLevel + "|" + notificationsToString() + "|" + this.numberOfTerminals() + "|" + _payments + "|" + _debt;
	}
}
