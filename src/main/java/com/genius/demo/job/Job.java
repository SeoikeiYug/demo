package com.genius.demo.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class Job {

    private static final Logger log = LoggerFactory.getLogger(Job.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private boolean executeFlag = false;

    // 发送ebd最大线程数量
    private Integer maxThreadNum = 12;

    // 等待发送的队列最大数量
    private Integer maxQueueCount = 200;

    @Scheduled(cron = "0 * * * * ?")
    public void reportCurrentTime() {
        log.info("The time is now {} start", dateFormat.format(new Date()));
        if (!executeFlag) {
            log.info("The time is now {} in", dateFormat.format(new Date()));
            executeFlag = true;
            // 阻塞队列,存放待执行的Order,相当于缓存.
            LinkedBlockingQueue<String> orderQueue = new LinkedBlockingQueue<String>(maxQueueCount);
            // 添加50个worker进去，每个worker从queue里面取一个order，超过50的就会自动在一个linkedBlockingQueue里面了，别担心！
            final ExecutorService executors = Executors.newFixedThreadPool(maxThreadNum);
            new Thread(new JobExecute(orderQueue, executors, maxThreadNum)).start();
            new Thread(new JobQuery(orderQueue, maxQueueCount)).start();
        }
    }

}
