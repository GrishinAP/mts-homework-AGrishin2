package hw10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleExample {
    private static int maxNumber = 1000;
    private static int threadNumber = 2;
    static List<Integer> resList = new ArrayList<>();

    public SimpleExample(int maxNumber, int threadNumber) {
        this.maxNumber = maxNumber;
        SimpleExample.threadNumber = threadNumber;
    }

    public List<Integer> getSimpleNumbers() {
        if (maxNumber == 0 || threadNumber == 0) return resList;
        if(maxNumber<=3){
            resList.add(1);
            return resList;
        } else if (maxNumber>=3) {
            resList.add(1);
            resList.add(3);
        }
        for (int i = 0; i < threadNumber; i++) {
            final int taskNumber = i+1;
            Thread thread = new Thread(() -> {
                int myStart = (maxNumber / threadNumber)*(taskNumber-1);
                if(myStart<=4) myStart=4;
                int myEnd =  taskNumber != threadNumber? (maxNumber/threadNumber)*taskNumber:maxNumber;
                for (int k = myStart; k < myEnd; k++) {
                    System.out.println(k+"----"+Math.sqrt(k));
                    boolean fl=true;
                        for(int j=2;j<=Math.sqrt(k);j++)
                            if (k % j == 0){
                                fl=false;
                                break;
                            }
                        if(fl){
                            resList.add(k);
                            System.out.println(" k "+k);
                        }
                    System.out.println("Thread "+ taskNumber+" starts from "+myStart + " to "+myEnd);
                }
            });

            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        resList=resList.stream().sorted().collect(Collectors.toList());
        System.out.println(resList);
        return resList;
    }


}

