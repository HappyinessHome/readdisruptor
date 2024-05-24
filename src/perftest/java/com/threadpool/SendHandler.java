package com.threadpool;


import com.lmax.disruptor.WorkHandler;

/**
 * 处理队列中需要发送的的数据 发送给隔离装置
 */
public class SendHandler implements WorkHandler<String> {




    @Override
    public void onEvent(String taskModel) throws Exception {
        System.out.println("test");

    }
}
