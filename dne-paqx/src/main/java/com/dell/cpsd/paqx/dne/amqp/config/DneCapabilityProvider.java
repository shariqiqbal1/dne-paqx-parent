package com.dell.cpsd.paqx.dne.amqp.config;

import com.dell.cpsd.hdp.capability.registry.capability.annotations.Capability;
import com.dell.cpsd.hdp.capability.registry.capability.annotations.CapabilityProvider;
import com.dell.cpsd.hdp.capability.registry.constants.AmqpExchangeTypes;
import com.dell.cpsd.hdp.capability.registry.dto.CapabilityDataModel;
import com.dell.cpsd.hdp.capability.registry.dto.RequestQueue;

@CapabilityProvider
public class DneCapabilityProvider
{

    @Capability(name="dne-test-capability")
    public CapabilityDataModel dneCapability(){
        
        RequestQueue requestQueue = new RequestQueue(); 
        requestQueue.setName("queue.dell.cpsd.dne.test.request");
        
        CapabilityDataModel capabilityDataModel = new CapabilityDataModel();
        
        capabilityDataModel.setRequestExchange("exchange.dell.cpsd.dne.test.request.exchange");
        capabilityDataModel.setRequestExchangeType(AmqpExchangeTypes.TOPIC);
        capabilityDataModel.setRequestMessageType("com.dell.cpsd.dne.test.request.message");
        capabilityDataModel.setRequestMessageVersion("com.dell.cpsd.dne.test.request.message.version");
        capabilityDataModel.setRequestQueue(requestQueue);
        capabilityDataModel.setRequestRoutingKey("com.dell.cpsd.dne.test.request.routing.key");
        
        
        capabilityDataModel.setResponseExchange("exchange.dell.cpsd.dne.test.response.exchange");
        capabilityDataModel.setResponseExchangeType(AmqpExchangeTypes.TOPIC);
        capabilityDataModel.setResponseMessageType("com.dell.cpsd.dne.test.response.message");
        capabilityDataModel.setResponseMessageVersion("com.dell.cpsd.dne.test.response.message.version");
        capabilityDataModel.setResponseRoutingKey("com.dell.cpsd.dne.test.response.routing.key{replyTo}");
        
        return capabilityDataModel;
        
    }
    
    @Capability(name="dne-test-event-capability",isEvent=true)
    public CapabilityDataModel dneEventCapability(){
        
        CapabilityDataModel capabilityDataModel = new CapabilityDataModel();
        
        capabilityDataModel.setEventExchange("exchange.dell.cpsd.dne.test.event.exchange");
        capabilityDataModel.setEventExchangeType(AmqpExchangeTypes.TOPIC);
        capabilityDataModel.setEventMessageType("com.dell.cpsd.dne.test.event.message");
        capabilityDataModel.setEventMessageVersion("com.dell.cpsd.dne.test.event.message.version");
        capabilityDataModel.setEventRoutingKey("com.dell.cpsd.dne.test.event.routing.key");
        
        return capabilityDataModel;
        
    }
}
