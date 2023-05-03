package prr.core;

public class TextCommunication extends Communication{
    private String _message;

    public TextCommunication(Terminal from, String idReceiver, String message){
        super(from, idReceiver); 
        super.setType("TEXT");
        super.setIsOnGoing(false);
        super.setSize(message.length());
        _message = message;
    }

    public String getMessage(){
        return _message;
    }

    public void computeCost(){
        BasicPlan plan = new BasicPlan();
        int cost = plan.computeCostText(this);
        super.setCost(cost);
        (super.getClient()).incrementDebt(cost);
        (super.getTerminalFrom()).incrementDebt(cost);
    }   

    public int getSize(){
        return _message.length();
    }
}
