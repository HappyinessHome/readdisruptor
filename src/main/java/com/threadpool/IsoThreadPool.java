package com.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class IsoThreadPool {
    public static ThreadPoolExecutor getThreadPoolByNum(int syncNum,ThreadFactory threadFactory){
        int cpus = Runtime.getRuntime().availableProcessors();
        if(cpus>=syncNum){
            syncNum=cpus;
        }
        long l=100L;
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(syncNum,syncNum,l, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),threadFactory);
        return threadPoolExecutor;
    }

}
