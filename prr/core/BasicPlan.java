package prr.core;

public class BasicPlan extends TariffPlan{

    @Override
    protected int computeCostText(TextCommunication message) {
        if((message.getClient()).getClientLevel() == ClientLevel.NORMAL){
            if(message.getSize() < 50){
                return 10;
            }
            if(50 <= message.getSize() && message.getSize() < 100){
                return 16;
            }
            if(message.getSize() >= 100){
                return 2 * message.getSize();
            }
        }

        if((message.getClient()).getClientLevel() == ClientLevel.GOLD){
            if(message.getSize() < 100){
                return 10;
            }
            if(message.getSize() >= 100){
                return 2 * message.getSize();
            }
        }

        if((message.getClient()).getClientLevel() == ClientLevel.PLATINUM){
            if(message.getSize() < 50){
                return 0;
            }
            if(message.getSize() >= 50){
                return 4;
            }
        }
        return 0;
    }

    @Override
    protected int computeCostIterative(InterativeCommunication ic){
        if(ic.getType().equals("VOICE")){
            if((ic.getClient()).getClientLevel() == ClientLevel.NORMAL){
                return 20 * ic.getSize();
            }
            if((ic.getClient()).getClientLevel() == ClientLevel.GOLD){
                return 10 * ic.getSize();
            }
            return 10 * ic.getSize();
        }

        if(ic.getType().equals("VIDEO")){
            if((ic.getClient()).getClientLevel() == ClientLevel.NORMAL){
                return 30 * ic.getSize();
            }
            if((ic.getClient()).getClientLevel() == ClientLevel.GOLD){
                return 20 * ic.getSize();
            }
            return 10 * ic.getSize();
        }
        return 0;
    }
}
