package prr.core;

abstract public class InterativeCommunication extends Communication{

    public InterativeCommunication(Terminal from, String idReceiver, String type){
        super(from, idReceiver);
        super.setType(type);
    }

    public int computeCost(InterativeCommunication ic){
        BasicPlan plan = new BasicPlan();
        if(ic.getType().equals("VIDEO")){
            int costVideo = plan.computeCostIterative(this);
            super.setCost(costVideo);
            (super.getClient()).incrementDebt(costVideo);  
            (super.getTerminalFrom()).incrementDebt(costVideo);
            return costVideo;  
        }
        if(ic.getType().equals("VOICE")){
            int costVoice = plan.computeCostIterative(this);
            super.setCost(costVoice);
            (super.getClient()).incrementDebt(costVoice);
            (super.getTerminalFrom()).incrementDebt(costVoice);
            return costVoice;
        }
        return 0;
    }
}
