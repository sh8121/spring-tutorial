package com.sh8121.springioccontainer.archive.v0_basic;

import com.sh8121.springioccontainer.archive.v0_basic.bean.v4.BeanA;
import com.sh8121.springioccontainer.archive.v0_basic.bean.v4.BeanAFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppFactoryBean {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppFactoryBean.class);
        var beanA0 = applicationContext.getBean(BeanA.class);
        System.out.println("beanA0 = " + beanA0);

        var beanA1 = (BeanA) applicationContext.getBean("beanA");
        System.out.println("beanA1 = " + beanA1);

        var beanAFactoryBean = (BeanAFactoryBean) applicationContext.getBean("&beanA");
        System.out.println("beanAFactoryBean = " + beanAFactoryBean);
    }

    @Bean(name = "beanA")
    BeanAFactoryBean beanAFactoryBean() {
        return new BeanAFactoryBean();
    }
}
