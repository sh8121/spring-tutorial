package com.sh8121.springioccontainer.v0_basic;

import com.sh8121.springioccontainer.v0_basic.bean.v0.BeanA;
import com.sh8121.springioccontainer.v0_basic.bean.v0.BeanB;
import com.sh8121.springioccontainer.v0_basic.bean.v0.BeanC;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigurationBean {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AppConfigurationBean.class);
        applicationContext.refresh();

        var beanA = (BeanA) applicationContext.getBean("beanA");
        var beanB = applicationContext.getBean("beanB", BeanB.class);
        var beanC = applicationContext.getBean(BeanC.class);

        System.out.println("beanA=" + beanA);
        System.out.println("beanB=" + beanB);
        System.out.println("beanC=" + beanC);
    }

    @Bean
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
}
