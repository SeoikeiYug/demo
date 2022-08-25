package com.genius.demo.job;

import com.genius.demo.controller.ExceptionController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class JobExecute implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(JobExecute.class);

    //待处理的指令任务队列
    private LinkedBlockingQueue<String> orderQueue;

    private ExecutorService executors;

    //最大线程数量
    private Integer maxThreadNum;

    public JobExecute(LinkedBlockingQueue<String> orderQueue, ExecutorService executors, Integer maxThreadNum) {
        this.orderQueue = orderQueue;
        this.executors = executors;
        this.maxThreadNum = maxThreadNum;
    }

    @Override
    public void run() {
        // 提交50个工作线程到池子
        for (int i = 1; i <= maxThreadNum; i++) {
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
//                        try {
//                            List<ExceptionController.User> list = new ArrayList<ExceptionController.User>();
//                            //- 循环创建的实例对象被list引用，所以无法被GC回收
//                            for (int i = 0; ; i++) {
//                                System.out.println("轮次：" + i);
//                                list.add(new ExceptionController.User(UUID.randomUUID().toString()));
//                            }
//                        } catch (Exception e) {
//
//                        }
                        if (!orderQueue.isEmpty()) {
                            try {
                                System.out.println(Thread.currentThread().getName() + ", 执行中的JobExecute！执行：" + orderQueue.take());
                                int i;
                                for (i = 0; i < 500000000; i++) {
                                    i++;
                                }
                                System.out.println(Thread.currentThread().getName() + ": " + i);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    public static void recursivePrint(Integer num) {
        System.out.println("ExceptionController Number: " + num);
        recursivePrint(++num);
    }

}