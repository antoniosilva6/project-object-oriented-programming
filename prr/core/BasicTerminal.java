package prr.core;


public class BasicTerminal extends Terminal{
	
	public BasicTerminal(String id, String idClient){
		super(id, idClient);
		super.setType("BASIC");
	}	
}
