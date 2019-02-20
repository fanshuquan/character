package com.fsq.character.concurrence.blockingqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * BlockingQueue 有多种实现，包括：
 * ArrayBlockingQueue
 * DelayQueue
 * LinkedBlockingQueue
 * PriorityBlockingQueue
 * SynchronousQueue
 * Created by fan on 19/2/18.
 */
public class BlockingQueueTest {
    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
        Runnable producer = () -> {
            try {
                queue.put("1");
                Thread.sleep(1000);
                queue.put("2");
                Thread.sleep(1000);
                queue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable consumer = () -> {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(5000);
    }

    @Test
    public void testPriorityBlockingQueue() throws InterruptedException {
        BlockingQueue queue   = new PriorityBlockingQueue();

        //String implements java.lang.Comparable
        queue.put("calue");
        queue.put("aalue");
        queue.put("balue");

        while (!queue.isEmpty()){
            System.out.println(queue.take());
        }

        for (Object aQueue : queue) {
            System.out.println(aQueue);
        }
    }

    @Test
    public void testDequeue() throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

        deque.addFirst("1");
        deque.addLast("2");

        System.out.println(deque.takeLast());
        System.out.println(deque.takeFirst());

    }

    @Test
    public void testConcurrentNavigableMap(){
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();

        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");

        ConcurrentNavigableMap headMap = map.headMap("2");
        System.out.println(headMap.descendingKeySet());
    }

    /**
     * 校验arraylist线程不安全
     * @throws InterruptedException
     */
    @Test
    public void testConcurrentProblem() throws InterruptedException {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Runnable producer1 = () -> {
            IntStream.range(0,100).forEach(value -> {
                arrayList.add(value);
            });
        };
        Runnable producer2 = () -> {
            IntStream.range(100,200).forEach(value -> {
                arrayList.add(value);
            });
        };

        Thread thread1 = new Thread(producer1);
        Thread thread2 = new Thread(producer2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(arrayList.size());
        arrayList.stream().forEach(value -> {
            System.out.println(value);
        });
    }

}
