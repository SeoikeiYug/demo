package com.genius.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteWorker implements Runnable {

    final ExecutorService executors = Executors.newFixedThreadPool(50);

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 50; i++) {
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + "ExecuteWorker在执行");
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new Thread(new ExecuteWorker()).start();
    }
}
