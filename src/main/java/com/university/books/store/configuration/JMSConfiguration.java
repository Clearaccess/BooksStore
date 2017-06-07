package com.university.books.store.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

/**
 * Created by Aleksandr on 6/7/2017.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.university.books.store")
@PropertySource(value = { "classpath:jms.properties" })
public class JMSConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getRequiredProperty("host"));
        mailSender.setPort(Integer.parseInt(environment.getRequiredProperty("port")));
        mailSender.setUsername("aleksandr-vanykov@yandex.ru");
        mailSender.setPassword(environment.getRequiredProperty("password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable",true);
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
