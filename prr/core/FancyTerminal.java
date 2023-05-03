package prr.core;


public class FancyTerminal extends Terminal{
	
	
	public FancyTerminal(String id, String idClient){
		super(id, idClient);
		super.setType("FANCY");
	}

}
