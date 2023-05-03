package prr.core;

abstract public class TariffPlan {

    abstract protected int computeCostText(TextCommunication message);

    abstract protected int computeCostIterative(InterativeCommunication ic);

}
