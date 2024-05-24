package com.threadpool;


import com.lmax.disruptor.EventFactory;

public class IsoEventFactory implements EventFactory<String> {
    @Override
    public String newInstance() {
        return  "test";
    }
}
