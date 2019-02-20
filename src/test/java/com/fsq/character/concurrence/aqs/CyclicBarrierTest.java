package com.fsq.character.concurrence.aqs;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by fan on 19/2/19.
 */
public class CyclicBarrierTest {
    /**
     * 所有线程必须等待的一个栅栏，直到所有线程都到达这里，然后所有线程才可以继续做其他事情
     * 多用于相同的任务分步骤完成，如
     * 所有任务先步骤一，再步骤二，步骤一用时不一定，步骤二的前提是所有线程必须都完成步骤一
     */
    private CyclicBarrier barrier = new CyclicBarrier(2);
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private Random r = new Random();
    @Test
    public void t(){
        IntStream.range(0,2).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int taskDuration = r.ints(1000,3000).findFirst().getAsInt();
                            Thread.sleep(taskDuration);
                            System.out.println(Thread.currentThread().getName());
                            System.out.println("finished,task duration:"+taskDuration);
                            barrier.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
