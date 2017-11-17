package com.dell.cpsd.paqx.dne.amqp.handler;

import com.dell.cpsd.event.subscriber.annotation.EventMessageHandler;
import com.dell.cpsd.event.subscriber.annotation.EventMessageListener;
import com.dell.cpsd.paqx.dne.rest.model.AboutInfo;
import com.dell.cpsd.storage.capabilities.api.CatalogService;

@EventMessageListener(queues="test.dne.event.queue")
public class DneEventHandler
{

    @EventMessageHandler(capability="dne-test-event-capability")
    public void handleDneEvent(CatalogService eventMessage){
        System.out.println("Received message from DNE Event "+ eventMessage.toString());
    }
}
