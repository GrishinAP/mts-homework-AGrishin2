package hw10;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public void runExampl(int threadNum, int incrNum) {
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 1; i <= threadNum; i++) {
            final int taskNumber = i;
            Thread thread = new Thread(() -> {
                int myCount = 0;
                for (int k = 0; k < incrNum; k++) {
                    myCount = count.incrementAndGet();
                }
            });
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("After all threads ended count = " + count.get());
    }


}
