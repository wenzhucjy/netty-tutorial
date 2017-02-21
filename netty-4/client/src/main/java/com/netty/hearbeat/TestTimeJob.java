package com.netty.hearbeat;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * description: 定时任务测试
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-12-05 9:47
 */
public class TestTimeJob {

    public static void main(String[] args) {
        Temp temp = new Temp();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleWithFixedDelay(
                temp, 0, 2, TimeUnit.SECONDS
        );
    }
}

class Temp extends Thread {
    @Override
    public void run() {
        System.out.println("run");
    }
}
