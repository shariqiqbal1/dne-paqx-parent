package com.dell.cpsd.paqx.dne.amqp.handler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.dell.cpsd.common.rabbitmq.config.HandlerRegistrar;
import com.dell.cpsd.common.rabbitmq.consumer.error.DefaultErrorTransformer;
import com.dell.cpsd.common.rabbitmq.consumer.error.ErrorTransformer;
import com.dell.cpsd.contract.extension.amqp.message.HasMessageProperties;
import com.dell.cpsd.paqx.dne.amqp.config.DnePropertiesConfig;
import com.dell.cpsd.paqx.dne.amqp.handler.SampleErrorMessage;
import com.dell.cpsd.MessageProperties;
@Configuration
@ComponentScan({"com.dell.cpsd.paqx.dne.amqp.handler"})
public class DneMessageHandlerConfig
{
    
    @Autowired
    private String               replyTo;

    @Autowired
    private HandlerRegistrar     handlerRegistrar;

    @Autowired
    private DnePropertiesConfig propertiesConfig;
    
    @Bean
    public DneTestHandler listDiscoveredNodesResponseHandler()
    {
        return new DneTestHandler(propertiesConfig.responseExchange(), messageErrorTransformer());
    }

    /**
     * Register Response Handler
     */
    @PostConstruct
    public void registerHandler()
    {
        handlerRegistrar.register(listDiscoveredNodesResponseHandler());
    }

    /**
     * Message Error Transformer
     * 
     * @return {@link ErrorTransformer<HasMessageProperties<?>>}
     */
    private ErrorTransformer<HasMessageProperties<?>> messageErrorTransformer()
    {
        return new DefaultErrorTransformer<>(propertiesConfig.responseExchange(), replyTo,
                () -> new SampleErrorMessage().withMessageProperties(new MessageProperties()), Error::new);
        
    }
   
    @Bean
    public DneEventHandler dneEventHandler(){
        return new DneEventHandler();
    }
    
    @Bean
    public DneMessageHandler dneMessageHandler(){
        return new DneMessageHandler();
    }
}
