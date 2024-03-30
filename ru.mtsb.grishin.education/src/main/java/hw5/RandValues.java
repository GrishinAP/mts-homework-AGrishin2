package hw5;

import java.util.ArrayList;

public class RandValues<T>{
    public  T listItem(ArrayList<T> lst){
        int ind= (int) ((lst.size())*Math.random());
       // System.out.println(lst.get(ind));
        return lst.get(ind);
    }
}

