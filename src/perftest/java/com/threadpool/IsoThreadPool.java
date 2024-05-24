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
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(syncNum,syncNum,100, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),threadFactory);
        return threadPoolExecutor;
    }
    public static ThreadPoolExecutor getThreadPoolBytargetCpus(int syncNum, ThreadFactory threadFactory){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(syncNum,syncNum,100, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),threadFactory);
        return threadPoolExecutor;
    }
}
