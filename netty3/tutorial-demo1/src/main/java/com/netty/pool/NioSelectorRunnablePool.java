package com.netty.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: selector 线程管理者
 *
 * @author : jy.chen
 * @version : 1.0
 * @since : 2016-11-24 14:51
 */
public class NioSelectorRunnablePool {

    /**
     * boss线程数组
     */
    private final AtomicInteger bossIndex   = new AtomicInteger();
    private Boss[]              bosses;

    /**
     * worker线程数组
     */
    private final AtomicInteger workerIndex = new AtomicInteger();
    private Worker[]            workeres;

    public NioSelectorRunnablePool(Executor boss, Executor worker){
        initBoss(boss, 1);
        initWorker(worker, Runtime.getRuntime().availableProcessors() * 2);
    }

    /**
     * 初始化boss线程
     * 
     * @param boss Executor
     * @param count count
     */
    private void initBoss(Executor boss, int count) {
        this.bosses = new NioServerBoss[count];
        for (int i = 0; i < bosses.length; i++) {
            bosses[i] = new NioServerBoss(boss, "boss thread " + (i + 1), this);
        }

    }

    /**
     * 初始化worker线程
     * 
     * @param worker Executor
     * @param count count
     */
    private void initWorker(Executor worker, int count) {
        this.workeres = new NioServerWorker[count];
        for (int i = 0; i < workeres.length; i++) {
            workeres[i] = new NioServerWorker(worker, "worker thread " + (i + 1), this);
        }
    }

    /**
     * 获取一个worker
     *
     * @return Worker
     */
    public Worker nextWorker() {
        return workeres[Math.abs(workerIndex.getAndIncrement() % workeres.length)];

    }

    /**
     * 获取一个boss
     * 
     * @return Boss
     */
    public Boss nextBoss() {
        return bosses[Math.abs(bossIndex.getAndIncrement() % bosses.length)];
    }

}
