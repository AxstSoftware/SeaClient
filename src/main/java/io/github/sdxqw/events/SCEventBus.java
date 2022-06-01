package io.github.sdxqw.events;

import com.google.common.eventbus.EventBus;

public class SCEventBus extends EventBus {

    @Override
    public void register(Object object) {
        super.register(object);
    }

    @Override
    public void unregister(Object object) {
        super.unregister(object);
    }

    @Override
    public void post(Object event) {
        super.post(event);
    }
}
