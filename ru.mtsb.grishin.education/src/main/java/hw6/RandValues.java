package hw6;

import java.util.List;

public class RandValues<T> {
    public T listItem(List<T> lst) {
        int ind = (int) ((lst.size()) * Math.random());
        // System.out.println(lst.get(ind));
        return lst.get(ind);
    }
}

