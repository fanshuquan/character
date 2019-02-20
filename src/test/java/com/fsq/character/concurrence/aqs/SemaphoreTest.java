package com.fsq.character.concurrence.aqs;

import org.junit.Test;

import java.util.concurrent.Semaphore;

/**
 * Created by fan on 19/2/19.
 */
public class SemaphoreTest {
    /**
     * 作用：
     * 1.保护一个重要(代码)部分防止一次超过 N 个线程进入。
     * 2.在两个线程之间发送信号。
     * @throws InterruptedException
     */
    @Test
    public void t() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
//        Semaphore semaphore = new Semaphore(1, true);//公平信号量

        semaphore.acquire();

        semaphore.release();
    }
}
