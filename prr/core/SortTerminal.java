package prr.core;

import java.util.Comparator;

public class SortTerminal implements Comparator<Terminal> {

    @Override
    public int compare(Terminal arg0, Terminal arg1) {
        return arg0.toString().compareTo(arg1.toString());
    }
}
