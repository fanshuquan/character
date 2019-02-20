package com.fsq.character.concurrence.aqs;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by fan on 19/2/19.
 */
public class ExchangerTest {
    /**
     * 表示一种两个线程可以进行互相交换对象的会和点,常用于线程间交换信息，事实上也起到了CyclicBarrier的作用
     * 两个线程通过一个 Exchanger 交换对象。
     * 交换对象的动作由 Exchanger 的两个 exchange() 方法的其中一个完成
     */
    private Exchanger exchanger = new Exchanger();
    private ExecutorService executorService = Executors.newFixedThreadPool(2);
    private Random r = new Random();
    @Test
    public void t()  {
        IntStream.range(0,2).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int taskDuration = r.ints(1000,3000).findFirst().getAsInt();
                            Thread.sleep(taskDuration);
                            System.out.println(Thread.currentThread().getName()+"_"+taskDuration);
                            String str = (String) exchanger.exchange(Thread.currentThread().getName());
                            System.out.println(Thread.currentThread().getName()+"_"+str);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
