package hw6;

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
                //System.out.println(this.getClass().getSimpleName() + "-" + taskNumber
                //        + " incremented count 100 times and getting value = " + myCount);
            });
            thread.start();
        }


        // Ждемc
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("After all threads ended count = " + count.get());
    }


}
