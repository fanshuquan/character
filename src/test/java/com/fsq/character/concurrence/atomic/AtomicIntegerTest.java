package com.fsq.character.concurrence.atomic;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Created by fan on 19/2/19.
 */
public class AtomicIntegerTest {
    private ExecutorService executorService = Executors.newFixedThreadPool(4);
    private int i = 0;
    private AtomicInteger ai = new AtomicInteger(0);

    @Test
    public void testIntegerAdd() {
        IntStream.range(0, 2000).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                    }
                });
            }
        });
        System.out.println(i);
    }

    @Test
    public void testAtomIntegerAdd(){
        IntStream.range(0, 2000).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        ai.addAndGet(1);
                    }
                });
            }
        });
        System.out.println(ai.get());
    }
}
