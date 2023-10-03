package com.sh8121.springioccontainer.v0_basic.bean.v3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanA implements InitializingBean, DisposableBean, BeanNameAware {

    public BeanA() {
        System.out.println("BeanA Created");
    }

    @Autowired
    public void setBeanB(BeanB beanB) {
        System.out.println("setBeanB");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("the name of BeanA is " + name);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("BeanA.postConstruct()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanA.afterPropertiesSet()");
    }

    public void customInit() {
        System.out.println("BeanA.customInit()");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("BeanA.preDestroy()");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanA.destroy()");
    }

    public void customDestroy() {
        System.out.println("BeanA.customDestroy()");
    }
}
