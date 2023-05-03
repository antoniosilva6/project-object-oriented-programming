package prr.core;

import java.util.Comparator;

public class SortClient implements Comparator<Client> {

    @Override
    public int compare(Client arg0, Client arg1) {
        return arg0.toString().compareTo(arg1.toString());
    }
}
