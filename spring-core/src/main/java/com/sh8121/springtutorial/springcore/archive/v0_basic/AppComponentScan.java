package com.sh8121.springtutorial.springcore.archive.v0_basic;

import com.sh8121.springtutorial.springcore.archive.v0_basic.bean.v1.BeanA;
import com.sh8121.springtutorial.springcore.archive.v0_basic.bean.v1.BeanB;
import com.sh8121.springtutorial.springcore.archive.v0_basic.bean.v1.BeanC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.sh8121.springtutorial.springcore.archive.v0_basic.bean.v1")
public class AppComponentScan {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppComponentScan.class);

        var beanA = (BeanA) applicationContext.getBean("beanA");
        var beanB = applicationContext.getBean("beanB", BeanB.class);
        var beanC = applicationContext.getBean(BeanC.class);

        System.out.println("beanA=" + beanA);
        System.out.println("beanB=" + beanB);
        System.out.println("beanC=" + beanC);
    }
}
