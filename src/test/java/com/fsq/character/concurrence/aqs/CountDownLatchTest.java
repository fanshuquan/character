package com.fsq.character.concurrence.aqs;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fan on 19/2/19.
 */
public class CountDownLatchTest {
    /**
     * 它允许一个或多个线程等待一系列指定操作的完成
     */
    private CountDownLatch countDownLatch = new CountDownLatch(2);
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    @Test
    public void t() throws InterruptedException {

        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        System.out.println("finished");
    }
}
