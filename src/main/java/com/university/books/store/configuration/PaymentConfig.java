package com.university.books.store.configuration;

import com.braintreegateway.BraintreeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Aleksandr on 5/30/2017.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan("com.university.books.store")
@PropertySource(value = { "classpath:payment.properties" })
public class PaymentConfig {

    @Autowired
    private Environment environment;

    @Bean
    public BraintreeGateway gateway(){
        BraintreeGateway gateway=new BraintreeGateway(
                com.braintreegateway.Environment.SANDBOX,
                environment.getRequiredProperty("MERCHANT_ID"),
                environment.getRequiredProperty("PUBLIC_KEY"),
                environment.getRequiredProperty("PRIVATE_KEY"));
        return gateway;
    }
}
