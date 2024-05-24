package threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class IsoGatewayThreadFactory implements ThreadFactory {
    private String prefix;
    private static AtomicInteger atomicInteger=new AtomicInteger();
    public IsoGatewayThreadFactory(String prefix){
        this.prefix=prefix;
    }
    @Override
    public Thread newThread(Runnable r) {
        StringBuilder name=new StringBuilder();
        name.append(prefix).append("-").append(atomicInteger.getAndIncrement());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread thread=new Thread(threadGroup,r,name.toString());
        if(!thread.isDaemon()){
            thread.setDaemon(false);
        }
        return thread;
    }
}
