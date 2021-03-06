/**
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries.  All Rights Reserved.
 * Dell EMC Confidential/Proprietary Information
 **/

package com.dell.cpsd.paqx.dne.amqp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * The configuration for the compliance data service message consumer.
 * <p>
 * <p/>
 * Copyright &copy; 2017 Dell Inc. or its subsidiaries. All Rights Reserved.
 * <p/>
 *
 * @version 1.0
 * @since SINCE-TBD
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:META-INF/spring/dne-paqx/persistence.properties")
public class PersistenceConfig
{
    @Value("${remote.dell.database.password}")
    private String databasePassword;

    @Value("${remote.dell.database.url}")
    private String databaseUrl;

    @Value("${remote.dell.database.username}")
    private String databaseUsername;

    @Value("${remote.dell.database.driver.class.name}")
    private String databaseDriverClassName;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DdlAuto;

    @Value("${hibernate.naming_strategy}")
    private String hibernateNamingStrategy;

    @Value("${hibernate.default_schema}")
    private String hibernateDefaultSchema;

    public DriverManagerDataSource dataSource()
    {
        final DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(databaseDriverClassName);
        source.setUrl(databaseUrl);
        source.setUsername(databaseUsername);
        source.setPassword(databasePassword);

        return source;
    }

    @Bean
    JpaTransactionManager transactionManager()
    {
        final JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory()
    {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactory.setPackagesToScan("com.dell.cpsd.paqx.dne.domain");

        final Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.hbm2ddl.auto", hibernateHBM2DdlAuto);
        properties.put("hibernate.naming_strategy", hibernateNamingStrategy);

        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    public JpaVendorAdapter jpaVendorAdapter()
    {
        final HibernateJpaVendorAdapter returnVal = new HibernateJpaVendorAdapter();
        returnVal.setDatabase(Database.H2);
        returnVal.setGenerateDdl(true);
        returnVal.setShowSql(true);

        return returnVal;
    }
}