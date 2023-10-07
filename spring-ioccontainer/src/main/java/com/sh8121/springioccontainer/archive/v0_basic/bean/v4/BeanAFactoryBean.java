package com.sh8121.springioccontainer.archive.v0_basic.bean.v4;

import org.springframework.beans.factory.FactoryBean;

public class BeanAFactoryBean implements FactoryBean<BeanA> {

    public BeanAFactoryBean() {
        System.out.println("BeanAFactoryBean Created");
    }

    @Override
    public BeanA getObject() throws Exception {
        return new BeanA();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanA.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
