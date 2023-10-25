package com.sh8121.springcore.archive.v0_basic;

import com.sh8121.springcore.archive.v0_basic.bean.v3.BeanA;
import com.sh8121.springcore.archive.v0_basic.bean.v3.BeanB;
import com.sh8121.springcore.archive.v0_basic.bean.v3.BeanC;
import com.sh8121.springcore.archive.v0_basic.bean.v3.BeanPostProcessorA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanLifeCycle {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppBeanLifeCycle.class);
        applicationContext.registerShutdownHook();
    }

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    BeanA beanA() {
        return new BeanA();
    }

    @Bean
    BeanB beanB() {
        return new BeanB();
    }

    @Bean
    BeanC beanC() {
        return new BeanC();
    }

    @Bean
    BeanPostProcessorA beanPostProcessorA() {
        return new BeanPostProcessorA();
    }
}
