package com.dell.cpsd.paqx.dne.amqp.config;

import com.dell.cpsd.paqx.dne.repository.ScaleIOComponentPersister;
import com.dell.cpsd.paqx.dne.repository.VCenterComponentPersister;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.dell.cpsd.paqx.dne.repository"})
@Import({VCenterComponentPersister.class, ScaleIOComponentPersister.class})
public class ComponentConfig
{
    @Bean
    @Qualifier("jack1")
    public VCenterComponentPersister vCenterComponentPersister()
    {
        return new VCenterComponentPersister();
    }

    @Bean
    @Qualifier("jack2")
    public ScaleIOComponentPersister scaleIOComponentPersister()
    {
        return new ScaleIOComponentPersister();
    }
}

