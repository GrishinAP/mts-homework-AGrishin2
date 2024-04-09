package hw6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SortExample {
    static List<Integer> list = new ArrayList();
    static List<List<Integer>> cursors = new ArrayList<>();
    private int threadNumber;
    private int randRange;
    private int listSize;

    public SortExample(int threadNumber, int randRange, int listSize) {
        this.threadNumber = threadNumber;
        this.randRange = randRange;
        this.listSize = listSize;
    }

    public List<Integer> getSortRandIntList() {
        RandList.fillRandList(listSize, randRange);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < threadNumber; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                int startIndex = list.size() / threadNumber * taskNumber;
                int endIndex = taskNumber != threadNumber - 1 ? startIndex + list.size() / threadNumber : listSize;
                List<Integer> subList = list.subList(startIndex, endIndex).stream().sorted().collect(Collectors.toList());
                syncAdd(cursors, startIndex, startIndex, endIndex);
                System.out.println("Task " + taskNumber + " my sublist is " + subList + "  " + startIndex + "  " + endIndex + " cursors=" + cursors);
                for (int j = startIndex; j < endIndex; j++) {
                    list.set(j, subList.get(j - startIndex));
                }
                System.out.println("Task " + taskNumber + ". Common List now is " + list);
            });
        }

        // Ждемc
        try {
            executor.awaitTermination(5, SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.println("Common List after all threads ended is " + list);

        List<Integer> resList = new ArrayList<>();
        int localCurssor = 0;
        int min = Integer.MAX_VALUE;
        while (list.size() > resList.size()) {
            //System.out.println("localCurssor="+localCurssor+" min="+min);
            for (List<Integer> cursor : cursors) {
                //System..out.println("+++++"+cursor);
                if (cursor.get(1) == cursor.get(2)) continue;
                if (list.get(cursor.get(1)) <= min) {
                    min = list.get(cursor.get(1));
                    localCurssor = cursors.indexOf(cursor);
                }
            }//for
            resList.add(min);
            min = Integer.MAX_VALUE;
            //System.out.println("------"+cursors+"    "+resList);
            cursors.set(localCurssor, Arrays.asList(cursors.get(localCurssor).get(0), cursors.get(localCurssor).get(1) + 1, cursors.get(localCurssor).get(2)));
        }//while

        System.out.println("resList is " + resList + "   list is " + list);

        return resList;
    }

    private synchronized void syncAdd(List l, int lf, int m, int r) {
        l.add(Arrays.asList(lf, m, r));
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