package hw6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SortExample {
    static List<Integer> list = new ArrayList();
    private  int threadNumber;
    private  int randRange;
    private  int listSize;

    public SortExample(int threadNumber, int randRange, int listSize){
        this.threadNumber=threadNumber;
        this.randRange=randRange;
        this.listSize=listSize;
    }
    public List<Integer> getSortRandIntList(){
        RandList.fillRandList(listSize,randRange);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadNumber; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                int startIndex  =list.size()/threadNumber*taskNumber;
                int endIndex    = taskNumber!=threadNumber-1?startIndex+list.size()/threadNumber:listSize;
                List<Integer> subList= list.subList(startIndex,endIndex).stream().sorted().collect(Collectors.toList());
                System.out.println("Task " + taskNumber + " my sublist is " + subList+ "  "+startIndex+ "  "+endIndex);
                for(int j=startIndex;j<endIndex;j++){
                    list.set(j,subList.get(j-startIndex));
                }
                System.out.println("Task " + taskNumber + ". Common List now is " + list);
            });
        }

        // Ждемc
        try{
        executor.awaitTermination(5,SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();


        System.out.println("Common List after all threads ended is " + list);


        return list.stream().sorted().collect(Collectors.toList());
    }
    static class RandList {
        public static void fillRandList(int listSize, int randRange) {
            Random rnd = new Random();
            for (int i = 0; i < listSize; i++) {
                list.add(rnd.nextInt(randRange));
            }
        }

    }
}
