package com.threadpool;


import com.lmax.disruptor.WorkHandler;


public class SendHandler implements WorkHandler<String> {




    @Override
    public void onEvent(String taskModel) throws Exception {
        System.out.println("test");

    }
}
