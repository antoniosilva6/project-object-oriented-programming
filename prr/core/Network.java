package prr.core;

import java.io.Serializable;
import java.io.IOException;

import prr.core.exception.UnrecognizedEntryException; 

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** Arrays */
    private List<Client> _clients;
	private List<Terminal> _terminals;
	private List<Communication> _communications;
	private List<InterativeCommunication> _voiceCommunications;
	private	List<InterativeCommunication> _videoCommunications;
	private List<Notification> _notifications;
	private List<String> _failedCommunications;

	/** Constructor */
	public Network(){
		_clients = new ArrayList<Client>();
		_terminals = new ArrayList<Terminal>();
		_communications = new ArrayList<Communication>();
		_videoCommunications = new ArrayList<InterativeCommunication>();
		_voiceCommunications = new ArrayList<InterativeCommunication>();
		_notifications = new ArrayList<Notification>();
		_failedCommunications = new ArrayList<String>();
	}
	
	/** Methods */

	/**
	 * This method will provide a “read-only” access to internal list of Clients.
	 * @return
	 */
	
	public List<Client> getAllClients(){
		return Collections.unmodifiableList(_clients);
	}
	
	/**
	 * This method will search for a Client with a certain id on the ArrayList of Clients already added to the system.
	 * 
	 * 
	 * @param id the id of the Client to be searched for
	 * @return returns the Client with the specific id, if the client doesn´t exist, the return value will be null.
	 */

	public Client getClient(String id){
		for (Client i : _clients){
      		if (id.equals(i.getId())){
        		return i;
      		}
    	}
    return null;
  }

  	/**
	 * This method will provide a “read-only” access to internal list of Terminals.
	 * @return
	 */
	
	public List<Terminal> getAllTerminals(){
		return Collections.unmodifiableList(_terminals);
	}
	
	/**
	 * This method will search for a terminal with a certain id on the ArrayList of terminals already added to the system.
	 * 
	 * @param id the id of the terminal to be searched for.
	 * @return return the terminal with the specific id entered. If there is no terminal with that id, the return value will be null.
	 */

	public Terminal getTerminal(String id){
		for (Terminal i : _terminals){
			if (id.equals(i.getId())){
		    	return i;
		    }
		}
		return null;
	}

	/**
	 * This method will return a list of all communications with the order of insertion.
	 * 
	 * @return the list of communications.
	 */
	public List<Communication> getAllCommunications(){
		return Collections.unmodifiableList(_communications);
	}

	/**
	 * This method will return a list of all Voice communications with the order of insertion.
	 * @return the list of all the voice type communications.
	 */
	public List<InterativeCommunication> getAllVoiceCommunications(){
		return Collections.unmodifiableList(_voiceCommunications);
	}

	/**
	 * This method will return a list of all video communications with the order of insertion.
	 * 
	 * @return the list of all voice type communications.
	 */
	public List<InterativeCommunication> getAllVideoCommunications(){
		return Collections.unmodifiableList(_videoCommunications);
	}

	/**
	 * This method takes in an id and looks through the communications list searching for that specific id.
	 * @param id , the id of the communication.
	 * @return   , the communication itself.
	 */
	public Communication getCommunication(int id){
		for (Communication i: _communications){
			if(id == i.getId()){
				return i;
			}
		}
		return null;
	}

	/**
	 * This method takes in an id and looks through the voice communications list searching for that specific id.
	 * @param id , the id of the communication.
	 * @return	 , the communication itself.
	 */
	public VoiceCommunication getVoiceCommunication(int id){
		for (InterativeCommunication i: _voiceCommunications){
			if(id == i.getId()){
				return (VoiceCommunication) i;
			}
		}
		return null;
	}

	/**
	 * This method takes in an id and looks through the communications list searching for that specific id.
	 * @param id , the id of the communication.
	 * @return	 , the communication itself.
	 */
	public VideoCommunication getVideoCommunication(int id){
		for (InterativeCommunication i: _videoCommunications){
			if(id == i.getId()){
				return (VideoCommunication) i;
			}
		}
		return null;
	}

	/**
	 * This method will list all notifications.
	 * @return	the list of notifications.
	 */
	public List<Notification> getAllNotifications(){
		return Collections.unmodifiableList(_notifications);
	}

	/**
     * This method will ad a notification to the list of notifications.
     * @param n , the notification itself.
     */
	public void addNotifications(Notification n){
		_notifications.add(n);
	}

	/**
     * This method will list all the failed communications.
     * @return  , the list of failed communications.
     */
	public List<String> getAllFailedCommunications(){
		return Collections.unmodifiableList(_failedCommunications);
	}

	/**
     * This method will get a failed communication.
     * @param idTerminal    , the id of the terminal who attempeted do start a communication.
     * @return              , returns the id of communication that failed.
     */
	public String getFailedCommunication(String idTerminal){
		for(String i: _failedCommunications){
			if(idTerminal.equals(i)){
				return i;
			}
		}
		return null;
	}

	/**
     * This method will add a new failed communication.
     * @param idTerminal    ,the id of the terminal who attemped to start the communication.
     */
	public void addFailedCommunication(String idTerminal){
		_failedCommunications.add(idTerminal);
	}

	/**
	 * This method will allow the option to add friends. 
	 * The first t will be the terminal id from the client who wants to add a friend, the second id, f, will be the terminal id of the friend that t wants to add.
	 * 
	 * @param t id of the terminal to add.
	 * @param f id of the terminal to be added.
	 * @throws UnrecognizedEntryException
	 */

	public void addFriend(String t, String f) throws UnrecognizedEntryException{
		Terminal friend = getTerminal(f);
		getTerminal(t).addFriend(friend);
	} 

	/**
	 * This method will sort the clients ArrayList.
	 */

	public void sortClients() {
		Collections.sort(_clients, new SortClient());
	}

	/**
	 * This method will sort the terminals ArrayList.
	 */

	public void sortTerminals() {
		Collections.sort(_terminals, new SortTerminal());
	}


	// Parsing
	
	/**
	 * This method will register a Client.
	 * 
	 * this method takes in an id, name, and tax number of the client to be added to the system.
	 * 
	 * @param id		the identifier of the new client.
	 * @param name		the name of the new client to be added .
	 * @param taxNumber the tax number of the client.
	 * @throws UnrecognizedEntryException if some entry is not the expected.
	 */

	public void registerClient(String id, String name, int taxNumber) throws IOException {
    	Client newClient = new Client(id, name, taxNumber);

		_clients.add(newClient);
    }

	/**
   * This method will register a Terminal and link it with a certain Client.
   * 
   * @param type type of the terminal, it can either be BASIC or FANCY.
   * @param id	 a string with 6 numberes that are going to be the terminal identification.
   * @param idClient the id of the client the registered terminal will be linked to.
   * @throws UnrecognizedEntryException if some entry is not correct
   */
  
  	public Terminal registerTerminal(String type, String id, String idClient) throws IOException { 
  		Terminal t = null;
  		if(type.equals("BASIC")) {
  			t = new BasicTerminal(id, idClient);
  		} if(type.equals("FANCY")) {
  			t = new FancyTerminal(id, idClient);
  		}
		
		_terminals.add(t);
		getClient(idClient).addTerminal(t);

		return t;
  	}
	
	/**
	 * this method will send a text message, a text type communication.
	 * @param from 			, id of the terminal reaching out.
	 * @param idReceiver	, id of the terminal trying to be reached. 
	 * @param message		, text message itself.
	 * @return				, returns the message sent.
	 */
	public TextCommunication sendTextCommunication(Terminal from, String idReceiver, String message){
		TextCommunication c = new TextCommunication(from, idReceiver, message);
		
		_communications.add(c);
		getTerminal(idReceiver).addCommunicationReceived(c);
		from.addCommunicationMade(c);
		
		Client i = getClient(from.getIdClient());
		c.setClient(i);

		return c;
	}

	/**
	 * this method will send a text message, a interative type communication.
	 * @param from 			, id of the terminal reaching out.
	 * @param idReceiver	, id of the terminal trying to be reached. 
	 * @param type			, the type of communication.
	 * @return				, returns the communication sent.
	 */
	public InterativeCommunication startIterativeCommunication(Terminal from, String idReceiver, String type){
		InterativeCommunication c = null;
		if(type.equals("VOICE")){
			c = new VoiceCommunication(from, idReceiver);
			_voiceCommunications.add(c);
		} if(type.equals("VIDEO")){
			c = new VideoCommunication(from, idReceiver);
			_videoCommunications.add(c);
		}

		_communications.add(c);
		getTerminal(idReceiver).addCommunicationReceived(c);
		from.addCommunicationMade(c);

		Client i = getClient(from.getIdClient());
		c.setClient(i);

		return c;
	}
	
	/**
	 * this method will create a notification.
	 * @param idSender	, the id of the terminal sending a notification.
	 * @param notificationType	, the type of notification.
	 * @return					, the notification.
	 */
	public Notification createNotification(String idSender, String notificationType){
		Notification n = null;
		if(notificationType.equals("O2S")){
			n = new Notification(idSender);
			n.setType("O2S");
		}
		if(notificationType.equals("O2I")){
			n = new Notification(idSender);
			n.setType("O2I");
		}
		if(notificationType.equals("B2S")){
			n = new Notification(idSender);
			n.setType("O2I");
		}
		else{
			n = new Notification(idSender);
			n.setType("O2I");	
		}
			
		_notifications.add(n);
		Client c = getClient((this.getTerminal(idSender)).getIdClient());
		c.addNotifications(n);

		return n;
	}

  /**
   * Read text input file and create corresponding domain entities.
   * 
   * @param filename name of the text input file
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   */
  
   void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
	Parser parser = new Parser(this);
	parser.parseFile(filename);
  }
}

