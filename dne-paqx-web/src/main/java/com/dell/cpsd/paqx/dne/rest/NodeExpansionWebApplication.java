/**
 * Startup application.
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries.  All Rights Reserved.
 * Dell EMC Confidential/Proprietary Information
 */
package com.dell.cpsd.paqx.dne.rest;

import com.dell.cpsd.MessageProperties;
import com.dell.cpsd.common.rabbitmq.config.HandlerRegistrar;
import com.dell.cpsd.common.rabbitmq.consumer.error.DefaultErrorTransformer;
import com.dell.cpsd.common.rabbitmq.consumer.error.ErrorTransformer;
import com.dell.cpsd.contract.extension.amqp.message.HasMessageProperties;
import com.dell.cpsd.hdp.capability.registry.capability.annotations.EnableCapabilityRegistration;
import com.dell.cpsd.hdp.capability.registry.client.lookup.config.CapabilityRegistryLookupManagerConfig;
import com.dell.cpsd.paqx.dne.amqp.config.AsynchronousNodeServiceConfig;
import com.dell.cpsd.paqx.dne.amqp.config.DnePropertiesConfig;
import com.dell.cpsd.paqx.dne.amqp.config.ServiceConfig;
import com.dell.cpsd.paqx.dne.amqp.handler.DneMessageHandlerConfig;
import com.dell.cpsd.paqx.dne.amqp.handler.DneTestHandler;
import com.dell.cpsd.paqx.dne.amqp.handler.Error;
import com.dell.cpsd.paqx.dne.amqp.handler.SampleErrorMessage;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
@EnableCapabilityRegistration
@Import({DneMessageHandlerConfig.class,ServiceConfig.class , AsynchronousNodeServiceConfig.class, CapabilityRegistryLookupManagerConfig.class })
public class NodeExpansionWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(NodeExpansionWebApplication.class, args);

        //TODO: Remove this its only for testing
        try
        {
            Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8555").start();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
}
