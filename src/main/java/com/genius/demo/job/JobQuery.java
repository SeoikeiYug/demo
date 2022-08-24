package com.genius.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;

public class JobQuery implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(JobQuery.class);

    //待处理的指令任务队列
    private LinkedBlockingQueue<String> orderQueue;

    private Integer maxQueryCountPerHalfMinute;

    public JobQuery(LinkedBlockingQueue<String> orderQueue, Integer maxQueryCountPerHalfMinute) {
        this.orderQueue = orderQueue;
        this.maxQueryCountPerHalfMinute = maxQueryCountPerHalfMinute;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Integer i = 0; i < maxQueryCountPerHalfMinute; i++) {
                    orderQueue.put("orders" + i);
                }
                System.out.println(Thread.currentThread().getName() + ", 执行中的JobQuery！添加");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
