package com.threadpool;

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

        //设置事件业务处理器---消费者
        disruptor.handleEventsWithWorkerPool(sendHandlers);

        disruptor.start();
        //获取ringbuffer环，用于接取生产者生产的事件
        RingBuffer<String> ringBuffer = disruptor.getRingBuffer();
        long next = ringBuffer.next();
        ringBuffer.publish(next);
    }
}
