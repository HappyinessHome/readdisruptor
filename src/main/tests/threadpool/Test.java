package threadpool;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class Test {
    public static void main(String[] args) {
       int sendQueueThreads=Runtime.getRuntime().availableProcessors();
        IsoGatewayThreadFactory isoThreadFactory=new IsoGatewayThreadFactory("isotaskhandle-");
        IsoEventFactory isoEventFactory=new IsoEventFactory();
        int bufferSize = 1024;
        Disruptor<String> disruptor = new Disruptor<>(isoEventFactory, bufferSize, isoThreadFactory,
                ProducerType.MULTI, new BlockingWaitStrategy());

        int sendHandlersNum=5;
        SendHandler sendHandlers[]=new SendHandler[sendHandlersNum];
        for(int i=0;i<sendHandlersNum;i++){
            sendHandlers[i]=new SendHandler();
        }

        disruptor.handleEventsWithWorkerPool(sendHandlers);

        disruptor.start();
        RingBuffer<String> ringBuffer = disruptor.getRingBuffer();
        long next = ringBuffer.next();
        ringBuffer.publish(next);
    }
}
