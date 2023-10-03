package com.sh8121.springioccontainer.v0_basic;

import com.sh8121.springioccontainer.v0_basic.bean.v0.BeanA;
import com.sh8121.springioccontainer.v0_basic.bean.v0.BeanB;
import com.sh8121.springioccontainer.v0_basic.bean.v0.BeanC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppClassPathXmlApplicationContext {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        var beanA = (BeanA) applicationContext.getBean("beanA");
        var beanB = applicationContext.getBean("beanB", BeanB.class);
        var beanC = applicationContext.getBean(BeanC.class);

        System.out.println("beanA=" + beanA);
        System.out.println("beanB=" + beanB);
        System.out.println("beanC=" + beanC);
    }
}
